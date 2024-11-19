package br.com.designpattern.comportamentais.mediator.service;

import br.com.designpattern.comportamentais.mediator.mediator.FlightBookingMediator;

public interface Component {
    void setMediator(FlightBookingMediator mediator);
}