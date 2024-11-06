package br.com.designpattern.estruturais.adapter.controller;

import br.com.designpattern.estruturais.adapter.controller.request.PaymentRequest;
import br.com.designpattern.estruturais.adapter.service.impl.PaymentProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentProviderService paymentProviderService;

    @PostMapping("/pay")
    public String pay(@RequestBody PaymentRequest request) {
        String provider = request.getProvider();
        double amount = request.getAmount();
        return paymentProviderService.processPaymentByProvider(provider, amount);
    }
}
