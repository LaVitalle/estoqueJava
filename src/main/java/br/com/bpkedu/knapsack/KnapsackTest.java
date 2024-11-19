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
        List<Item> items = generateRandomItems(numberOfItems, 5, 50.0, 0.5, 300.0);


//        List<Item> items = new ArrayList<>();
//        items.add(new Item(40.0, 100.0));
//        items.add(new Item(5.0, 50.0));
//        items.add(new Item(6.0, 190.0));
//        items.add(new Item(2.0, 15.0));
//        items.add(new Item(8.0, 55.0));
//        items.add(new Item(4.5, 105.0));



        items.forEach(item -> System.out.println(item));


        // Criar uma mochila com a capacidade definida e a lista de itens gerados
        Knapsack knapsack = new Knapsack(capacity, items);

        // Resolver o problema da mochila com o solver
        KnapsackSolverBacktracking solver = new KnapsackSolverBacktracking();
        KnapsackState bestState = solver.solve(knapsack);

        // Exibir os resultados
        System.out.println("Itens selecionados para a mochila:");
        for (Item item : bestState.selectedItems) {
            System.out.printf("Id:%s, Peso: %.2f, Valor: %.2f%n", item.getId().toString(), item.getWeight(), item.getValue());
        }
        System.out.printf("Peso total: %.2f%n", bestState.getTotalWeight());
        System.out.printf("Valor total: %.2f%n", bestState.getTotalValue());


    }

    // Função para gerar itens aleatórios com valores decimais
    private static List<Item> generateRandomItems(int count, double minWeight, double maxWeight, double minValue, double maxValue) {
        List<Item> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {

            double weight = minWeight + (maxWeight - minWeight) * random.nextDouble();
            double value = minValue + (maxValue - minValue) * random.nextDouble();
            items.add(new Item(i+1, weight, value));
        }

        return items;
    }
}
