package br.com.designpattern.comportamentais.state.state.impl;

import br.com.designpattern.comportamentais.state.model.PaymentContext;
import br.com.designpattern.comportamentais.state.state.PaymentState;

// Estado Concluído
public class CompletedState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Pagamento já foi concluído.");
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Pagamento concluído não pode ser cancelado.");
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Pagamento já está concluído.");
    }
}
