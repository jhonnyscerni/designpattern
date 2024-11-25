package br.com.designpattern.criacionais.builder;

public class GeradorDeContratos {
    public static void main(String[] args) {
        // Contrato para Pessoa Física
        ContratoDeEmprestimo contratoPF = new ContratoDeEmprestimo.Builder("João Silva", 50000)
                .taxaJuros(1.5)
                .prazo(24)
                .tipoCliente("PF")
                .seguro(true)
                .build();

        // Contrato para Pessoa Jurídica
        ContratoDeEmprestimo contratoPJ = new ContratoDeEmprestimo.Builder("Empresa XYZ", 200000)
                .taxaJuros(1.2)
                .prazo(36)
                .tipoCliente("PJ")
                .garantia("Imóvel Comercial")
                .build();

        // Imprime os contratos
        System.out.println(contratoPF);
        System.out.println(contratoPJ);
    }
}
