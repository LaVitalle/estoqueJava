package br.com.bpkedu.knapsack;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KnapsackSolverBacktracking {
    private static final int MAX_QUEUE_SIZE = 1000;
    private static final int MAX_DEPTH = 50;

    public KnapsackState solve(Knapsack knapsack) {
        List<Produto> produtos = knapsack.getProduto());
        double capacity = knapsack.getCapacity();

        PriorityQueue<KnapsackState> openList = new PriorityQueue<>(Comparator.comparingDouble(this::calculatePriority).reversed());

        KnapsackState initialState = new KnapsackState();
        openList.add(initialState);
        KnapsackState bestState = initialState;

        while (!openList.isEmpty() && openList.size() <= MAX_QUEUE_SIZE) {
            KnapsackState current = openList.poll();

            if (current.getTotalValor() > bestState.getTotalValor() && current.getTotalArea() <= capacity) {
                bestState = current;
            }

            if (current.selectedProdutos.size() < MAX_DEPTH) {
                for (Produto produto : produtos) {
                    if (!current.selectedProdutos.contains(produto)) {
                        KnapsackState newState = new KnapsackState(current.selectedProdutos);
                        newState.addProduto(produto);

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

    private double calculatePriority(KnapsackState state) {
        return state.getTotalValor() / (state.getTotalArea() + 1e-5);
    }

    /**
     * Calcula um limite superior (bound) para o valor de um estado usando uma aproximação fracionária.
     *
     * @param state O estado atual da mochila.
     * @param produtos A lista completa de itens.
     * @param capacity A capacidade total da mochila.
     * @return O valor máximo potencial para o estado.
     */
    private double calculateBound(KnapsackState state, List<Produto> produtos, double capacity) {
        double bound = state.getTotalValor();
        double remainingCapacity = capacity - state.getTotalArea();

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
