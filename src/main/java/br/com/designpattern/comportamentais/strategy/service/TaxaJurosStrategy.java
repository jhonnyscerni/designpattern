package br.com.designpattern.comportamentais.strategy.service;

import java.math.BigDecimal;

public interface TaxaJurosStrategy {
    BigDecimal calcularTaxa(BigDecimal valorEmprestimo, int prazo);
}
