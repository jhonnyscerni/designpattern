package br.com.designpattern.criacionais.abstractfactory.factory;

import br.com.designpattern.criacionais.abstractfactory.model.Transacao;
import br.com.designpattern.criacionais.abstractfactory.model.impl.TransacaoTED;
import br.com.designpattern.criacionais.abstractfactory.model.ProcessadorTaxa;
import br.com.designpattern.criacionais.abstractfactory.model.impl.ProcessadorTaxaTED;
import br.com.designpattern.criacionais.abstractfactory.model.Validador;
import br.com.designpattern.criacionais.abstractfactory.model.impl.ValidadorTED;

public class TEDFactory implements TransacaoFactory {
    @Override
    public Transacao criarTransacao() {
        return new TransacaoTED();
    }

    @Override
    public Validador criarValidador() {
        return new ValidadorTED();
    }

    @Override
    public ProcessadorTaxa criarProcessadorTaxa() {
        return new ProcessadorTaxaTED();
    }
}
