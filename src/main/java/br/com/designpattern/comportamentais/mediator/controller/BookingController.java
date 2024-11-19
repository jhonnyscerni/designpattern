package br.com.designpattern.comportamentais.mediator.controller;

import br.com.designpattern.comportamentais.mediator.service.AvailabilityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final AvailabilityService availabilityService;

    public BookingController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/mediator/book-flight")
    public String bookFlight() {
        availabilityService.reserveSeat();
        return "Flight booking initiated!";
    }
}