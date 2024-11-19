package br.com.designpattern.comportamentais.strategy.strategy;

import java.math.BigDecimal;

public interface TaxaJurosStrategy {
    BigDecimal calcularTaxa(BigDecimal valorEmprestimo, int prazo);
}
