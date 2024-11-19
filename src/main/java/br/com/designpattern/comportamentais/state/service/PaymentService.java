package br.com.designpattern.comportamentais.state.service;

import br.com.designpattern.comportamentais.state.model.PaymentContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    private Map<String, PaymentContext> payments = new HashMap<>();

    public String createPayment(String paymentId) {
        payments.put(paymentId, new PaymentContext());
        return "Pagamento criado com ID: " + paymentId;
    }

    public String processPayment(String paymentId) {
        PaymentContext context = payments.get(paymentId);
        if (context != null) {
            context.process();
            return "Pagamento processado.";
        }
        return "Pagamento não encontrado.";
    }

    public String cancelPayment(String paymentId) {
        PaymentContext context = payments.get(paymentId);
        if (context != null) {
            context.cancel();
            return "Pagamento cancelado.";
        }
        return "Pagamento não encontrado.";
    }

    public String completePayment(String paymentId) {
        PaymentContext context = payments.get(paymentId);
        if (context != null) {
            context.complete();
            return "Pagamento concluído.";
        }
        return "Pagamento não encontrado.";
    }
}