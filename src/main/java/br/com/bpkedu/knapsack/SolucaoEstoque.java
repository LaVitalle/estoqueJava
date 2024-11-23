package br.com.bpkedu.knapsack;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

<<<<<<< HEAD:src/main/java/br/com/bpkedu/knapsack/KnapsackSolverBacktracking.java
public class KnapsackSolverBacktracking {
    private static final int MAX_QUEUE_SIZE = 1000;
    private static final int MAX_DEPTH = 50;

    public KnapsackState solve(Knapsack knapsack) {
        List<Produto> produtos = knapsack.getProduto());
        double capacity = knapsack.getCapacity();

        PriorityQueue<KnapsackState> openList = new PriorityQueue<>(Comparator.comparingDouble(this::calculatePriority).reversed());
=======
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
>>>>>>> 774fe04a072b54fd4a7f7af4405a42ff7fbb5eb7:src/main/java/br/com/bpkedu/knapsack/SolucaoEstoque.java

        EstoqueStatus initialState = new EstoqueStatus();
        openList.add(initialState);
        EstoqueStatus bestState = initialState;

        while (!openList.isEmpty() && openList.size() <= MAX_QUEUE_SIZE) {
            EstoqueStatus current = openList.poll();

            if (current.getTotalValor() > bestState.getTotalValor() && current.getTotalArea() <= capacity) {
                bestState = current;
            }

<<<<<<< HEAD:src/main/java/br/com/bpkedu/knapsack/KnapsackSolverBacktracking.java
            if (current.selectedProdutos.size() < MAX_DEPTH) {
                for (Produto produto : produtos) {
                    if (!current.selectedProdutos.contains(produto)) {
                        KnapsackState newState = new KnapsackState(current.selectedProdutos);
                        newState.addProduto(produto);
=======
            // Expande o estado atual se não tiver atingido o limite de profundidade
            if (current.selectedItems.size() < MAX_DEPTH) {
                for (Item item : items) {
                    if (!current.selectedItems.contains(item)) {
                        EstoqueStatus newState = new EstoqueStatus(current.selectedItems);
                        newState.addItem(item);
>>>>>>> 774fe04a072b54fd4a7f7af4405a42ff7fbb5eb7:src/main/java/br/com/bpkedu/knapsack/SolucaoEstoque.java

                        if (newState.getTotalArea() <= capacity && calculateBound(newState, produtos, capacity) > bestState.getTotalValor()) {
                            openList.add(newState);

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

<<<<<<< HEAD:src/main/java/br/com/bpkedu/knapsack/KnapsackSolverBacktracking.java
    private double calculatePriority(KnapsackState state) {
        return state.getTotalValor() / (state.getTotalArea() + 1e-5);
=======
    /**
     * Calcula a prioridade de um estado com base no valor total e na heurística.
     *
     * @param state O estado da mochila.
     * @return A prioridade calculada para o estado.
     */
    private double calculatePriority(EstoqueStatus state) {
        return state.getTotalValue() / (state.getTotalWeight() + 1e-5); // Evita divisão por zero
>>>>>>> 774fe04a072b54fd4a7f7af4405a42ff7fbb5eb7:src/main/java/br/com/bpkedu/knapsack/SolucaoEstoque.java
    }

    /**
     * Calcula um limite superior (bound) para o valor de um estado usando uma aproximação fracionária.
     *
     * @param state O estado atual da mochila.
     * @param produtos A lista completa de itens.
     * @param capacity A capacidade total da mochila.
     * @return O valor máximo potencial para o estado.
     */
<<<<<<< HEAD:src/main/java/br/com/bpkedu/knapsack/KnapsackSolverBacktracking.java
    private double calculateBound(KnapsackState state, List<Produto> produtos, double capacity) {
        double bound = state.getTotalValor();
        double remainingCapacity = capacity - state.getTotalArea();
=======
    private double calculateBound(EstoqueStatus state, List<Item> items, double capacity) {
        double bound = state.getTotalValue();
        double remainingCapacity = capacity - state.getTotalWeight();
>>>>>>> 774fe04a072b54fd4a7f7af4405a42ff7fbb5eb7:src/main/java/br/com/bpkedu/knapsack/SolucaoEstoque.java

        for (Produto produto : produtos) {
            if (!state.selectedProdutos.contains(produto)) {
                if (produto.getArea() <= remainingCapacity) {
                    bound += produto.getValor();
                    remainingCapacity -= produto.getArea();
                } else {
                    bound += produto.getValor() * (remainingCapacity / produto.getArea());
                    break;
                }
            }
        }

        return bound;
    }

}
