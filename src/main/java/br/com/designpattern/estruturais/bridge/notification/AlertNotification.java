package br.com.designpattern.estruturais.bridge.notification;

import br.com.designpattern.estruturais.bridge.sender.MessageSender;
import br.com.designpattern.estruturais.bridge.Notification;

public class AlertNotification extends Notification {

    public AlertNotification(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending alert notification: " + message);
        messageSender.sendMessage(message);
    }
}
