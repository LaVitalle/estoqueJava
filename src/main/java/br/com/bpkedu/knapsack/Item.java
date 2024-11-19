package br.com.bpkedu.knapsack;

public class Item {
    private Integer id;
    private Double weight;
    private Double value;

    public Item(Integer id, Double weight, Double value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id= " + id +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }
}
