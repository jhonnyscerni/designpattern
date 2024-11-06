package br.com.designpattern.bridge.notification;

import br.com.designpattern.bridge.sender.MessageSender;
import br.com.designpattern.bridge.Notification;

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
