package br.com.bpkedu.knapsack;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SolucaoEstoque {
    // Define um limite para a profundidade e para o tamanho da fila de prioridade para evitar o consumo excessivo de memória.
    private static final int MAX_QUEUE_SIZE = 1000;  // Limite de tamanho da fila de prioridade
    private static final int MAX_DEPTH = 50;         // Limite de profundidade de expansão

    /**
     * Resolve o problema da mochila utilizando uma versão otimizada do Algoritmo A* com limite de profundidade e controle de memória.
     *
     * @param estoque A instância de Knapsack que contém a capacidade máxima e a lista de itens disponíveis.
     * @return O estado da mochila (KnapsackState) que representa a melhor solução encontrada, contendo os itens selecionados.
     */
    public EstoqueStatus solve(Estoque estoque) {
        List<Item> items = estoque.getItems();
        double capacity = estoque.getCapacity();

        // Fila de prioridade para armazenar estados
        PriorityQueue<EstoqueStatus> openList = new PriorityQueue<>(Comparator.comparingDouble(this::calculatePriority).reversed());

        EstoqueStatus initialState = new EstoqueStatus();
        openList.add(initialState);
        EstoqueStatus bestState = initialState;

        while (!openList.isEmpty() && openList.size() <= MAX_QUEUE_SIZE) {
            EstoqueStatus current = openList.poll();

            // Atualiza o melhor estado se necessário
            if (current.getTotalValue() > bestState.getTotalValue() && current.getTotalWeight() <= capacity) {
                bestState = current;
            }

            // Expande o estado atual se não tiver atingido o limite de profundidade
            if (current.selectedItems.size() < MAX_DEPTH) {
                for (Item item : items) {
                    if (!current.selectedItems.contains(item)) {
                        EstoqueStatus newState = new EstoqueStatus(current.selectedItems);
                        newState.addItem(item);

                        // Adiciona o novo estado à fila apenas se ele respeitar o limite de capacidade
                        // e tiver potencial para superar a melhor solução atual
                        if (newState.getTotalWeight() <= capacity && calculateBound(newState, items, capacity) > bestState.getTotalValue()) {
                            openList.add(newState);

                            // Limita o tamanho da fila de prioridade para evitar estouro de memória
                            if (openList.size() > MAX_QUEUE_SIZE) {
                                openList.poll();
                            }
                        }
                    }
                }
            }
        }

        return bestState;
    }

    /**
     * Calcula a prioridade de um estado com base no valor total e na heurística.
     *
     * @param state O estado da mochila.
     * @return A prioridade calculada para o estado.
     */
    private double calculatePriority(EstoqueStatus state) {
        return state.getTotalValue() / (state.getTotalWeight() + 1e-5); // Evita divisão por zero
    }

    /**
     * Calcula um limite superior (bound) para o valor de um estado usando uma aproximação fracionária.
     *
     * @param state O estado atual da mochila.
     * @param items A lista completa de itens.
     * @param capacity A capacidade total da mochila.
     * @return O valor máximo potencial para o estado.
     */
    private double calculateBound(EstoqueStatus state, List<Item> items, double capacity) {
        double bound = state.getTotalValue();
        double remainingCapacity = capacity - state.getTotalWeight();

        for (Item item : items) {
            if (!state.selectedItems.contains(item)) {
                if (item.getWeight() <= remainingCapacity) {
                    bound += item.getValue();
                    remainingCapacity -= item.getWeight();
                } else {
                    bound += item.getValue() * (remainingCapacity / item.getWeight());
                    break;
                }
            }
        }

        return bound;
    }

}
