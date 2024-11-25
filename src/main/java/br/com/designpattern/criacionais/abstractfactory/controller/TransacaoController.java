package br.com.designpattern.criacionais.abstractfactory.controller;

import br.com.designpattern.criacionais.abstractfactory.factory.TEDFactory;
import br.com.designpattern.criacionais.abstractfactory.factory.TransacaoFactory;
import br.com.designpattern.criacionais.abstractfactory.model.Transacao;
import br.com.designpattern.criacionais.abstractfactory.model.ProcessadorTaxa;
import br.com.designpattern.criacionais.abstractfactory.model.Validador;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @PostMapping("/{tipo}")
    public ResponseEntity<String> processarTransacao(@PathVariable String tipo) {
        TransacaoFactory factory = null;

        switch (tipo.toUpperCase()) {
            case "TED":
                factory = new TEDFactory();
                break;
            case "DOC":
                //factory = new DOCFactory();
                break;
            case "BOLETO":
                //factory = new BoletoFactory();
                break;
            default:
                return ResponseEntity.badRequest().body("Tipo de transação inválido.");
        }

        // Usando a fábrica para criar os objetos
        Transacao transacao = factory.criarTransacao();
        Validador validador = factory.criarValidador();
        ProcessadorTaxa processadorTaxa = factory.criarProcessadorTaxa();

        if (validador.validar(transacao)) {
            double taxa = processadorTaxa.calcularTaxa(transacao);
            transacao.processar();
            return ResponseEntity.ok("Transação processada com sucesso. Taxa: " + taxa);
        } else {
            return ResponseEntity.badRequest().body("Falha na validação da transação.");
        }
    }
}
