package br.com.designpattern.comportamentais.chainofresponsibility.controller;

import br.com.designpattern.comportamentais.chainofresponsibility.model.Pedido;
import br.com.designpattern.comportamentais.chainofresponsibility.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/processar-pedido")
    public ResponseEntity<String> processarPedido(@RequestBody Pedido pedido) {
        boolean pedidoValido = pedidoService.processarPedido(pedido);
        if (!pedidoValido) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido inválido");
        }
        return ResponseEntity.ok("Pedido processado com sucesso");
    }
}
