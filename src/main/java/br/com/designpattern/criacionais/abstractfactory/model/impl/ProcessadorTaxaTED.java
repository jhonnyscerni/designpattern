package br.com.designpattern.criacionais.abstractfactory.model.impl;

import br.com.designpattern.criacionais.abstractfactory.model.ProcessadorTaxa;
import br.com.designpattern.criacionais.abstractfactory.model.Transacao;

public class ProcessadorTaxaTED implements ProcessadorTaxa {
    @Override
    public double calcularTaxa(Transacao transacao) {
        System.out.println("Calculando taxa para TED...");
        return 5.50;
    }
}