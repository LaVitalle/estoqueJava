package br.com.bpkedu.knapsack;

import java.util.*;

public class EstoqueSet {

    public EstoqueStatus solve(Estoque estoque) {
        System.out.println("Solucionando o problema de otimização");
        System.out.println("Ordenando os itens...");
        List<Item> sortedItems = new ArrayList<>(estoque.getItems());
        sortedItems.sort((a, b) -> Double.compare(b.getValue() / b.getWeight(), a.getValue() / a.getWeight()));

        System.out.println("Criando fila de prioridade para explorar os estados com base no valor total");
        PriorityQueue<EstoqueStatus> openList = new PriorityQueue<>(Comparator.comparingDouble(EstoqueStatus::getTotalValue).reversed());
        openList.add(new EstoqueStatus());

        EstoqueStatus bestState = null;

        System.out.println("Buscando a melhor combinação...");
        while (!openList.isEmpty()) {
            EstoqueStatus current = openList.poll();


            if (current.getTotalWeight() <= estoque.getCapacity() &&
                    (bestState == null || current.getTotalValue() > bestState.getTotalValue())) {
                System.out.println("Substituindo a combinação: " + bestState + " por " + current);
                bestState = current;
            }

            for (Item item : sortedItems) {
                if (current.getTotalWeight() + item.getWeight() <= estoque.getCapacity() &&
                        !current.selectedItems.contains(item)) {
                    EstoqueStatus newState = new EstoqueStatus(current.selectedItems);
                    newState.addItem(item);
                    openList.add(newState);
                }
            }
        }

        return bestState;
    }
}