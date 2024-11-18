package br.com.designpattern.comportamentais.observer;

import org.springframework.stereotype.Component;

@Component
public class BuyerNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Buyer notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
