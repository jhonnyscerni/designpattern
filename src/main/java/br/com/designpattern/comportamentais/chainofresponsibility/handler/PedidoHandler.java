package br.com.designpattern.comportamentais.chainofresponsibility.handler;

import br.com.designpattern.comportamentais.chainofresponsibility.model.Pedido;

public interface PedidoHandler {
    boolean processar(Pedido pedido);
}
