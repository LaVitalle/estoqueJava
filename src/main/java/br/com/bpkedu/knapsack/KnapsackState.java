package br.com.bpkedu.knapsack;

import br.com.bpkedu.knapsack.Item;

import java.util.ArrayList;
import java.util.List;

public class KnapsackState {

    protected final List<Produto> selectedProdutos;

    protected double totalArea;

    protected double totalValor;

    public KnapsackState() {
        this.selectedProdutos = new ArrayList<>();
        this.totalArea = 0.0;
        this.totalValor = 0.0;
    }

    /**
     * Construtor que inicializa o estado com uma lista específica de itens.
     * Calcula automaticamente o peso total e o valor total com base nos itens fornecidos.
     *
     * @param selectedProdutos A lista de itens a ser usada para inicializar o estado.
     */
    public KnapsackState(List<Produto> selectedProjeto) {
        this.selectedProdutos = new ArrayList<>(selectedProdutos);
        for (Produto produto : selectedProdutos) {
            this.totalArea += produto.getArea();
            this.totalValor += produto.getValor();
        }
    }

    public void addProduto(Produto produto) {
        selectedProdutos.add(produto);
        totalArea += produto.getArea();
        totalValor += produto.getValor();
    }

    public double getTotalArea() {
        return totalArea;
    }

    public double getTotalValor() {
        return totalValor;
    }

    /**
     * Calcula a heurística para o estado atual, que representa a diferença entre
     * a capacidade da mochila e o peso total dos itens selecionados.
     * A heurística é usada para priorizar estados que estão mais próximos
     * da capacidade da mochila sem ultrapassá-la.
     *
     * @param capacity A capacidade máxima da mochila.
     * @return A heurística para o estado atual (diferença entre capacidade e peso total).
     */
    public double heuristic(double capacity) {
        return capacity - totalArea;
    }

    @Override
    public String toString() {
        return "KnapsackState{" +
                "totalArea=" + totalArea +
                ", totalValor=" + totalValor +
                '}';
    }
}
