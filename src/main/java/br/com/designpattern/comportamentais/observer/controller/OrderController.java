package br.com.designpattern.comportamentais.observer.controller;

import br.com.designpattern.comportamentais.observer.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/observer/create-order")
    public String createOrder(@RequestParam String orderId) {
        orderService.createOrder(orderId);
        return "Order created!";
    }

    @GetMapping("/observer/approve-payment")
    public String approvePayment(@RequestParam String orderId) {
        orderService.approvePayment(orderId);
        return "Payment approved!";
    }

    @GetMapping("/observer/ship-order")
    public String shipOrder(@RequestParam String orderId) {
        orderService.shipOrder(orderId);
        return "Order shipped!";
    }

    @GetMapping("/fail-payment")
    public String failPayment(@RequestParam String orderId) {
        orderService.paymentFailed(orderId);
        return "Payment failed!";
    }
}
