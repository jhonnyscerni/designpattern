package br.com.designpattern.comportamentais.state.state.impl;

import br.com.designpattern.comportamentais.state.model.PaymentContext;
import br.com.designpattern.comportamentais.state.state.PaymentState;

// Estado Cancelado
public class CanceledState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Não é possível processar um pagamento cancelado.");
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Pagamento já foi cancelado.");
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Não é possível concluir um pagamento cancelado.");
    }
}