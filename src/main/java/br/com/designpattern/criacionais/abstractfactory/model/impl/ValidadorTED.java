package br.com.designpattern.criacionais.abstractfactory.model.impl;

import br.com.designpattern.criacionais.abstractfactory.model.Transacao;
import br.com.designpattern.criacionais.abstractfactory.model.Validador;

public class ValidadorTED implements Validador {
    @Override
    public boolean validar(Transacao transacao) {
        System.out.println("Validando TED...");
        return true;
    }
}