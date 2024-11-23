package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private double capacity;
    private List<Produto> produtos;


    public Knapsack(int capacity) {
        this.capacity = capacity;
        this.produtos = new ArrayList<>();
    }

    public Knapsack(double capacity, List<Produto> produtos) {
        this.capacity = capacity;
        this.produtos = produtos;
    }

    public void addProduto(Produto produto){
        this.produtos.add(produto);
    }

    public List<Produto> getProduto() {
        return produtos;
    }

    public double getCapacity() {
        return capacity;
    }
}
