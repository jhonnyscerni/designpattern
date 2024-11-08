package br.com.designpattern.comportamentais.chainofresponsibility.model;

public class Pedido {

    private Long id;
    private String cliente;
    private String status;

    public Pedido(Long id, String cliente) {
        this.id = id;
        this.cliente = cliente;
        this.status = "Novo";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", cliente='" + cliente + "', status='" + status + "'}";
    }
}
