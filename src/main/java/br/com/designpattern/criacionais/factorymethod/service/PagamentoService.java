package br.com.designpattern.criacionais.factorymethod.service;

import br.com.designpattern.criacionais.factorymethod.factory.PagamentoFactory;
import br.com.designpattern.criacionais.factorymethod.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoFactory pagamentoFactory;

    public void realizarPagamento(double valor, String tipo) {
        Pagamento pagamento = pagamentoFactory.criarPagamento(tipo);
        pagamento.processar(valor);
    }
}

