package br.com.designpattern.comportamentais.observer;

import org.springframework.stereotype.Component;

@Component
public class InternalTeamNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Internal team notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
