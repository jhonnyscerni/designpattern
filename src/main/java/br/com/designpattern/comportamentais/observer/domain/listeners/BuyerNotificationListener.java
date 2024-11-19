package br.com.designpattern.comportamentais.observer.domain.listeners;

import br.com.designpattern.comportamentais.observer.domain.events.Event;
import br.com.designpattern.comportamentais.observer.service.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BuyerNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Buyer notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
