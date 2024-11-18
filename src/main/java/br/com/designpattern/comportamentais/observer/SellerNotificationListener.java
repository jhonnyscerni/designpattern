package br.com.designpattern.comportamentais.observer;

import org.springframework.stereotype.Component;

@Component
public class SellerNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Seller notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
