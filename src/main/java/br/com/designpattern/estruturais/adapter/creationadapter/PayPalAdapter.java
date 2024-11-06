package br.com.designpattern.estruturais.adapter.creationadapter;

import br.com.designpattern.estruturais.adapter.model.PaymentInfo;
import br.com.designpattern.estruturais.adapter.model.external.PayPalPaymentInfo;
import org.springframework.stereotype.Component;

@Component
public class PayPalAdapter {

    public PaymentInfo adapt(PayPalPaymentInfo payPalPaymentInfo) {
        return new PaymentInfo(payPalPaymentInfo.getPaymentAmount());
    }
}
