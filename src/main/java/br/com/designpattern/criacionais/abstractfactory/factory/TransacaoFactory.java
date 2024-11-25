package br.com.designpattern.criacionais.abstractfactory.factory;

import br.com.designpattern.criacionais.abstractfactory.model.ProcessadorTaxa;
import br.com.designpattern.criacionais.abstractfactory.model.Transacao;
import br.com.designpattern.criacionais.abstractfactory.model.Validador;

// Abstract Factory
public interface TransacaoFactory {
    Transacao criarTransacao();
    Validador criarValidador();
    ProcessadorTaxa criarProcessadorTaxa();
}
