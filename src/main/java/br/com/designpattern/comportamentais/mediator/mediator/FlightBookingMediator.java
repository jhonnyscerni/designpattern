package br.com.designpattern.comportamentais.mediator.mediator;


import br.com.designpattern.comportamentais.mediator.service.Component;

public interface FlightBookingMediator {
    void notify(Component sender, String event);
}
