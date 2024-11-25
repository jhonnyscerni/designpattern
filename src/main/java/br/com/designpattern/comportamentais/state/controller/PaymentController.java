package br.com.designpattern.comportamentais.state.controller;

import br.com.designpattern.comportamentais.state.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("statePaymentController")
@RequestMapping("/state/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{paymentId}/create")
    public ResponseEntity<String> createPayment(@PathVariable String paymentId) {
        String response = paymentService.createPayment(paymentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{paymentId}/process")
    public ResponseEntity<String> processPayment(@PathVariable String paymentId) {
        String response = paymentService.processPayment(paymentId);
        if (response.equals("Pagamento não encontrado.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<String> cancelPayment(@PathVariable String paymentId) {
        String response = paymentService.cancelPayment(paymentId);
        if (response.equals("Pagamento não encontrado.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{paymentId}/complete")
    public ResponseEntity<String> completePayment(@PathVariable String paymentId) {
        String response = paymentService.completePayment(paymentId);
        if (response.equals("Pagamento não encontrado.")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}