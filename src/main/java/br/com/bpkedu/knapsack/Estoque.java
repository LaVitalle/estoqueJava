package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:src/main/java/br/com/bpkedu/knapsack/Knapsack.java
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
=======
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
>>>>>>> 774fe04a072b54fd4a7f7af4405a42ff7fbb5eb7:src/main/java/br/com/bpkedu/knapsack/Estoque.java
    }

    public void addProduto(Produto produto){
        this.produtos.add(produto);
    }

    public List<Produto> getProduto() {
        return produtos;
    }

    public double getCapacity() {
        return area;
    }
}
