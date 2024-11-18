package br.com.designpattern.comportamentais.observer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final NotificationManager notificationManager;
    private final SellerNotificationListener sellerListener;
    private final BuyerNotificationListener buyerListener;
    private final InternalTeamNotificationListener internalTeamListener;

    @Autowired
    public OrderController(OrderService orderService, NotificationManager notificationManager,
                           SellerNotificationListener sellerListener,
                           BuyerNotificationListener buyerListener,
                           InternalTeamNotificationListener internalTeamListener) {
        this.orderService = orderService;
        this.notificationManager = notificationManager;
        this.sellerListener = sellerListener;
        this.buyerListener = buyerListener;
        this.internalTeamListener = internalTeamListener;

        // Configurar assinaturas
        notificationManager.subscribe("ORDER_CREATED", sellerListener);
        notificationManager.subscribe("PAYMENT_APPROVED", sellerListener);
        notificationManager.subscribe("ORDER_SHIPPED", buyerListener);
        notificationManager.subscribe("PAYMENT_FAILED", internalTeamListener);
    }

    @GetMapping("/create-order")
    public String createOrder(@RequestParam String orderId) {
        orderService.createOrder(orderId);
        return "Order created!";
    }

    @GetMapping("/approve-payment")
    public String approvePayment(@RequestParam String orderId) {
        orderService.approvePayment(orderId);
        return "Payment approved!";
    }

    @GetMapping("/ship-order")
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
