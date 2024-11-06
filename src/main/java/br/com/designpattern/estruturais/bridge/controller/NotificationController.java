package br.com.designpattern.bridge.controller;

import br.com.designpattern.bridge.Notification;
import br.com.designpattern.bridge.notification.AlertNotification;
import br.com.designpattern.bridge.notification.ReportNotification;
import br.com.designpattern.bridge.sender.EmailSender;
import br.com.designpattern.bridge.sender.MessageSender;
import br.com.designpattern.bridge.sender.SmsSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @GetMapping("/alert")
    public void sendAlertNotification() {
        MessageSender emailSender = new EmailSender();
        Notification alertNotification = new AlertNotification(emailSender);
        alertNotification.sendNotification("This is an alert notification.");
    }

    @GetMapping("/report")
    public void sendReportNotification() {
        MessageSender smsSender = new SmsSender();
        Notification reportNotification = new ReportNotification(smsSender);
        reportNotification.sendNotification("This is a report notification.");
    }
}
