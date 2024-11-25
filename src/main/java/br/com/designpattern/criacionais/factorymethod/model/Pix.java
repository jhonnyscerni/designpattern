package br.com.designpattern.criacionais.factorymethod.model;

import org.springframework.stereotype.Component;

@Component
public class Pix implements Pagamento {
    @Override
    public void processar(double valor) {
        System.out.println("Processando pagamento de " + valor + " com Pix.");
    }
}