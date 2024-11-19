package br.com.designpattern.comportamentais.state.state.impl;

import br.com.designpattern.comportamentais.state.model.PaymentContext;
import br.com.designpattern.comportamentais.state.state.PaymentState;

// Estado Pendente
public class PendingState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Pagamento está sendo processado...");
        context.setState(new ProcessingState());
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Pagamento pendente foi cancelado.");
        context.setState(new CanceledState());
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Não é possível concluir um pagamento pendente.");
    }
}
