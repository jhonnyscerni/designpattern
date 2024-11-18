package br.com.designpattern.comportamentais.mediator.service;

import br.com.designpattern.comportamentais.mediator.Component;
import br.com.designpattern.comportamentais.mediator.FlightBookingMediator;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements Component {

    private FlightBookingMediator mediator;

    @Override
    public void setMediator(FlightBookingMediator mediator) {
        this.mediator = mediator;
    }

    public void processPayment() {
        System.out.println("PaymentService: Processing payment...");
        mediator.notify(this, "paymentProcessed");
    }
}