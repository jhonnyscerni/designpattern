package br.com.designpattern.comportamentais.state.state;

import br.com.designpattern.comportamentais.state.model.PaymentContext;

public interface PaymentState {
    void process(PaymentContext context);
    void cancel(PaymentContext context);
    void complete(PaymentContext context);
}
