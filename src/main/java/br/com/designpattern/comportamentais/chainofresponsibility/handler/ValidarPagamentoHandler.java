package br.com.designpattern.comportamentais.chainofresponsibility.handler;

import br.com.designpattern.comportamentais.chainofresponsibility.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class ValidarPagamentoHandler implements PedidoHandler {

    @Override
    public boolean processar(Pedido pedido) {
        // Lógica para validar o pagamento
        if (!pagamentoValido(pedido)) {
            return false; // Falha na verificação de pagamento
        }
        return true;
    }

    private boolean pagamentoValido(Pedido pedido) {
        // Implementação fictícia de validação de pagamento
        return true;
    }
}
