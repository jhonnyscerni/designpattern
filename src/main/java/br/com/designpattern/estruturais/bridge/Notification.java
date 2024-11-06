package br.com.designpattern.bridge;

import br.com.designpattern.bridge.sender.MessageSender;

public abstract class Notification {
    protected MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public abstract void sendNotification(String message);
}
