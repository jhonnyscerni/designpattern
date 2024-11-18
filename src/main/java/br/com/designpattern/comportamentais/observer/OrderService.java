package br.com.designpattern.comportamentais.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final NotificationManager notificationManager;

    @Autowired
    public OrderService(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public void createOrder(String orderId) {
        System.out.println("Order created with ID: " + orderId);
        Event event = new Event("ORDER_CREATED", "New order created.", orderId);
        notificationManager.notify(event);
    }

    public void approvePayment(String orderId) {
        System.out.println("Payment approved for order ID: " + orderId);
        Event event = new Event("PAYMENT_APPROVED", "Payment approved.", orderId);
        notificationManager.notify(event);
    }

    public void shipOrder(String orderId) {
        System.out.println("Order shipped with ID: " + orderId);
        Event event = new Event("ORDER_SHIPPED", "Order shipped.", orderId);
        notificationManager.notify(event);
    }

    public void paymentFailed(String orderId) {
        System.out.println("Payment failed for order ID: " + orderId);
        Event event = new Event("PAYMENT_FAILED", "Payment failed.", orderId);
        notificationManager.notify(event);
    }
}
