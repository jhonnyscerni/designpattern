package br.com.designpattern.comportamentais.chainofresponsibility.service;

import br.com.designpattern.comportamentais.chainofresponsibility.model.Pedido;
import br.com.designpattern.comportamentais.chainofresponsibility.handler.PedidoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final List<PedidoHandler> handlers;

    @Autowired
    public PedidoService(List<PedidoHandler> handlers) {
        this.handlers = handlers;
    }

    public boolean processarPedido(Pedido pedido) {
        for (PedidoHandler handler : handlers) {
            if (!handler.processar(pedido)) {
                return false; // Interrompe o processamento se alguma validação falhar
            }
        }
        return true; // Todas as validações foram bem-sucedidas
    }
}
