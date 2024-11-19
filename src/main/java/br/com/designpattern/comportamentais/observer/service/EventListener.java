package br.com.designpattern.comportamentais.observer.service;

import br.com.designpattern.comportamentais.observer.domain.events.Event;

public interface EventListener {
    void update(Event event);
}

