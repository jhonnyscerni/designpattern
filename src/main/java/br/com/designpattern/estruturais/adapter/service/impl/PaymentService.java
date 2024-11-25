package br.com.designpattern.estruturais.adapter.service.impl;

import br.com.designpattern.estruturais.adapter.creationadapter.PayPalAdapter;
import br.com.designpattern.estruturais.adapter.creationadapter.StripeAdapter;
import br.com.designpattern.estruturais.adapter.model.PaymentInfo;
import br.com.designpattern.estruturais.adapter.model.external.PayPalPaymentInfo;
import br.com.designpattern.estruturais.adapter.model.external.StripeChargeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adapterPaymentService")
public class PaymentService {

    private final PayPalAdapter payPalAdapter;
    private final StripeAdapter stripeAdapter;

    @Autowired
    public PaymentService(PayPalAdapter payPalAdapter, StripeAdapter stripeAdapter) {
        this.payPalAdapter = payPalAdapter;
        this.stripeAdapter = stripeAdapter;
    }

    public void processPayment(Object paymentInfo) {
        PaymentInfo info;

        if (paymentInfo instanceof PayPalPaymentInfo) {
            info = payPalAdapter.adapt((PayPalPaymentInfo) paymentInfo);
        } else if (paymentInfo instanceof StripeChargeInfo) {
            info = stripeAdapter.adapt((StripeChargeInfo) paymentInfo);
        } else {
            throw new IllegalArgumentException("Tipo de pagamento desconhecido.");
        }

        System.out.println("Processando pagamento de: " + info.getAmount());
    }
}
