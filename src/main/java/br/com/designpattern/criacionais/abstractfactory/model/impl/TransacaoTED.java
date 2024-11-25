package br.com.designpattern.criacionais.abstractfactory.model.impl;

import br.com.designpattern.criacionais.abstractfactory.model.Transacao;

// Implementações para TED
public class TransacaoTED implements Transacao {
    @Override
    public void processar() {
        System.out.println("Processando TED...");
    }
}