package br.com.designpattern.comportamentais.strategy.service.impl;

import br.com.designpattern.comportamentais.strategy.service.TaxaJurosStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("taxaBaixoRisco")
public class TaxaJurosBaixoRisco implements TaxaJurosStrategy {

    @Override
    public BigDecimal calcularTaxa(BigDecimal valorEmprestimo, int prazo) {
        // Algoritmo específico para clientes de baixo risco
        return valorEmprestimo.multiply(new BigDecimal("0.05")).multiply(new BigDecimal(prazo));
    }
}