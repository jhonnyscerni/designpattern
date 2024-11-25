package br.com.designpattern.criacionais.factorymethod.controller;


import br.com.designpattern.criacionais.factorymethod.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public String processarPagamento(@RequestParam double valor, @RequestParam String tipo) {
        try {
            pagamentoService.realizarPagamento(valor, tipo);
            return "Pagamento processado com sucesso!";
        } catch (IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        }
    }
}
