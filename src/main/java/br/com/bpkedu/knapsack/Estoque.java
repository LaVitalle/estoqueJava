package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private double area;
    private List<Item> items;


    public Estoque(int capacity) {
        this.area = capacity;
        this.items = new ArrayList<>();
    }

    public Estoque(double capacity, List<Item> itens) {
        this.area = capacity;
        this.items = itens;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public double getCapacity() {
        return area;
    }
}
