package br.com.designpattern.criacionais.builder;

public class ContratoDeEmprestimo {
    private final String cliente;
    private final double valorEmprestimo;
    private final double taxaJuros;
    private final int prazo;
    private final String tipoCliente;
    private final boolean seguro;
    private final String garantia;

    // Construtor privado
    private ContratoDeEmprestimo(Builder builder) {
        this.cliente = builder.cliente;
        this.valorEmprestimo = builder.valorEmprestimo;
        this.taxaJuros = builder.taxaJuros;
        this.prazo = builder.prazo;
        this.tipoCliente = builder.tipoCliente;
        this.seguro = builder.seguro;
        this.garantia = builder.garantia;
    }

    // Classe Builder interna
    public static class Builder {
        private String cliente;
        private double valorEmprestimo;
        private double taxaJuros;
        private int prazo;
        private String tipoCliente;
        private boolean seguro = false; // Default
        private String garantia = null; // Default

        public Builder(String cliente, double valorEmprestimo) {
            this.cliente = cliente;
            this.valorEmprestimo = valorEmprestimo;
        }

        public Builder taxaJuros(double taxaJuros) {
            this.taxaJuros = taxaJuros;
            return this;
        }

        public Builder prazo(int prazo) {
            this.prazo = prazo;
            return this;
        }

        public Builder tipoCliente(String tipoCliente) {
            this.tipoCliente = tipoCliente;
            return this;
        }

        public Builder seguro(boolean seguro) {
            this.seguro = seguro;
            return this;
        }

        public Builder garantia(String garantia) {
            this.garantia = garantia;
            return this;
        }

        public ContratoDeEmprestimo build() {
            return new ContratoDeEmprestimo(this);
        }
    }

    @Override
    public String toString() {
        return "ContratoDeEmprestimo{" +
                "cliente='" + cliente + '\'' +
                ", valorEmprestimo=" + valorEmprestimo +
                ", taxaJuros=" + taxaJuros +
                ", prazo=" + prazo +
                ", tipoCliente='" + tipoCliente + '\'' +
                ", seguro=" + seguro +
                ", garantia='" + garantia + '\'' +
                '}';
    }
}
