package br.com.designpattern.comportamentais.mediator;

import br.com.designpattern.comportamentais.mediator.service.AvailabilityService;
import br.com.designpattern.comportamentais.mediator.service.NotificationService;
import br.com.designpattern.comportamentais.mediator.service.PaymentService;

@org.springframework.stereotype.Component
public class FlightBookingMediatorImpl implements FlightBookingMediator {

    private final AvailabilityService availabilityService;
    private final PaymentService paymentService;
    private final NotificationService notificationService;

    public FlightBookingMediatorImpl(AvailabilityService availabilityService,
                                     PaymentService paymentService,
                                     NotificationService notificationService) {
        this.availabilityService = availabilityService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    @Override
    public void notify(Component sender, String event) {
        if (event.equals("seatReserved")) {
            paymentService.processPayment();
        } else if (event.equals("paymentProcessed")) {
            notificationService.sendNotification();
        }
    }
}
