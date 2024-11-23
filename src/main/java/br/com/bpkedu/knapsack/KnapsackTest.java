package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapsackTest {
    public static void main(String[] args) {

        // Configurações de capacidade e quantidade de itens
        Double capacity = 50.0;
        int numberOfItems = 50000;

        // Gerar itens aleatórios com valores decimais
        List<Produto> produto = generateRandomProduto(numberOfItems, 5, 50.0, 0.5, 300.0);


//        List<Item> items = new ArrayList<>();
//        items.add(new Item(40.0, 100.0));
//        items.add(new Item(5.0, 50.0));
//        items.add(new Item(6.0, 190.0));
//        items.add(new Item(2.0, 15.0));
//        items.add(new Item(8.0, 55.0));
//        items.add(new Item(4.5, 105.0));



        produto.forEach(item -> System.out.println(produto));

        Estoque knapsack = new Estoque(capacity, produto);

        SolucaoEstoque solver = new SolucaoEstoque();
        EstoqueStatus bestState = solver.solve(knapsack);

        System.out.println("Itens selecionados para a mochila:");
        for (Produto item : bestState.selectedProdutos) {
            System.out.printf("Id:%s, Peso: %.2f, Valor: %.2f%n", item.getId().toString(), item.getArea(), item.getValor());
        }
        System.out.printf("Peso total: %.2f%n", bestState.getTotalArea());
        System.out.printf("Valor total: %.2f%n", bestState.getTotalValor());


    }
    // Função para gerar itens aleatórios com valores decimais
    private static List<Produto> generateRandomItems(int count, double minArea, double maxArea, double minValor, double maxValor) {
        List<Produto> items = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < count; i++) {

            double area = minArea + (maxArea - minArea) * random.nextDouble();
            double valor = minValor + (maxValor - minValor) * random.nextDouble();
            items.add(new Produto(i+1, area, valor));
        }

        return items;
    }
}
