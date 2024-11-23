package br.com.bpkedu.knapsack;

public class Produto {
    private Integer id;
    private Double area;
    private Double valor;

    public Produto(Integer id, Double area, Double valor) {
        this.id = id;
        this.area = area;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public Double getArea() {
        return area;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id= " + id +
                ", area=" + area +
                ", valor=" + valor +
                '}';
    }
}
