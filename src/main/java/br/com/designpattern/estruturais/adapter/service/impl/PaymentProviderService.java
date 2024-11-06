package br.com.designpattern.adapter.service.impl;

import br.com.designpattern.adapter.model.external.PayPalPaymentInfo;
import br.com.designpattern.adapter.model.external.StripeChargeInfo;
import org.springframework.stereotype.Service;

@Service
public class PaymentProviderService {

    // Manter PaymentProcessingService e PaymentService separados para aderir ao Princípio da Responsabilidade Única (SRP).
    // PaymentProcessingService lida com a lógica de determinar o provedor de pagamento e processar o pagamento.
    // PaymentService é responsável pela lógica real de processamento de pagamento, interagindo com sistemas de pagamento externos.


    private final PaymentService paymentService;

    public PaymentProviderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String processPaymentByProvider(String provider, double amount) {
        switch (provider.toLowerCase()) {
            case "paypal":
                return processPayPalPayment(amount);
            case "stripe":
                return processStripePayment(amount);
            default:
                return "Provedor de pagamento desconhecido.";
        }
    }

    private String processPayPalPayment(double amount) {
        PayPalPaymentInfo payPalPaymentInfo = new PayPalPaymentInfo(amount);
        paymentService.processPayment(payPalPaymentInfo);
        return "Pagamento processado com sucesso para o valor: " + amount;
    }

    private String processStripePayment(double amount) {
        StripeChargeInfo stripeChargeInfo = new StripeChargeInfo(amount);
        paymentService.processPayment(stripeChargeInfo);
        return "Pagamento processado com sucesso para o valor: " + amount;
    }
}