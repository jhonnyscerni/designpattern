package br.com.designpattern.comportamentais.state.model;

import br.com.designpattern.comportamentais.state.state.PaymentState;
import br.com.designpattern.comportamentais.state.state.impl.PendingState;

public class PaymentContext {
    private PaymentState state;

    public PaymentContext() {
        this.state = new PendingState(); // Estado inicial
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public void process() {
        state.process(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void complete() {
        state.complete(this);
    }
}
