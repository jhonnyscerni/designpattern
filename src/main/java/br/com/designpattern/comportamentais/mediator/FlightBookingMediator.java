package br.com.designpattern.comportamentais.mediator;


public interface FlightBookingMediator {
    void notify(Component sender, String event);
}
