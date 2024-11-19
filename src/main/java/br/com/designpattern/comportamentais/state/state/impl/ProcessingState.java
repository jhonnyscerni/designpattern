package br.com.designpattern.comportamentais.state.state.impl;

import br.com.designpattern.comportamentais.state.model.PaymentContext;
import br.com.designpattern.comportamentais.state.state.PaymentState;

// Estado Processando
public class ProcessingState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Pagamento já está sendo processado.");
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Cancelando pagamento em processamento...");
        context.setState(new CanceledState());
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Pagamento processado com sucesso!");
        context.setState(new CompletedState());
    }
}