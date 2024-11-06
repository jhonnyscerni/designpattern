package br.com.designpattern.estruturais.bridge.notification;

import br.com.designpattern.estruturais.bridge.sender.MessageSender;
import br.com.designpattern.estruturais.bridge.Notification;

public class ReportNotification extends Notification {
    public ReportNotification(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending report notification: " + message);
        messageSender.sendMessage(message);
    }
}
