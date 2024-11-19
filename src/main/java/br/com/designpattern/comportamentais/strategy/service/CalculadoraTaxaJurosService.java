package br.com.designpattern.comportamentais.strategy.service;

import br.com.designpattern.comportamentais.strategy.strategy.TaxaJurosStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CalculadoraTaxaJurosService {

    private final Map<String, TaxaJurosStrategy> strategies;

    @Autowired
    public CalculadoraTaxaJurosService(Map<String, TaxaJurosStrategy> strategies) {
        this.strategies = strategies;
    }

    public BigDecimal calcularTaxa(String perfilCliente, BigDecimal valorEmprestimo, int prazo) {
        TaxaJurosStrategy strategy = strategies.get(perfilCliente);
        if (strategy == null) {
            throw new IllegalArgumentException("Perfil de cliente desconhecido");
        }
        return strategy.calcularTaxa(valorEmprestimo, prazo);
    }
}
