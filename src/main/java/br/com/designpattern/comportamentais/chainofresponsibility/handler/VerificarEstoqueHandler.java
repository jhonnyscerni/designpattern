package br.com.designpattern.comportamentais.chainofresponsibility.handler;

import br.com.designpattern.comportamentais.chainofresponsibility.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class VerificarEstoqueHandler implements PedidoHandler {

    @Override
    public boolean processar(Pedido pedido) {
        // Lógica para verificar o estoque
        if (!estoqueDisponivel(pedido)) {
            return false; // Falha na verificação de estoque
        }
        return true; // Passa para o próximo handler
    }

    private boolean estoqueDisponivel(Pedido pedido) {
        // Implementação fictícia de verificação de estoque
        return true;
    }
}
