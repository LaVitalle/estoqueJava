package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class EstoqueStatus {

    protected final List<Item> selectedItems;

    protected double totalWeight;

    protected double totalValue;

    public EstoqueStatus () {
        this.selectedItems = new ArrayList<>();
        this.totalWeight = 0.0;
        this.totalValue = 0.0;
    }

    public EstoqueStatus(List<Item> selectedItems) {
        this.selectedItems = new ArrayList<>(selectedItems);
        for (Item item : selectedItems) {
            this.totalWeight += item.getWeight();
            this.totalValue += item.getValue();
        }
    }

    public void addItem(Item item) {
        selectedItems.add(item);
        totalWeight += item.getWeight();
        totalValue += item.getValue();
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double heuristic(double capacity) {
        return capacity - totalWeight;
    }

    @Override
    public String toString() {
        return "KnapsackState{" +
                "totalWeight=" + totalWeight +
                ", totalValue=" + totalValue +
                '}';
    }
}
