package br.com.designpattern.adapter.creationadapter;

import br.com.designpattern.adapter.model.PaymentInfo;
import br.com.designpattern.adapter.model.external.StripeChargeInfo;
import org.springframework.stereotype.Component;

@Component
public class StripeAdapter {

    public PaymentInfo adapt(StripeChargeInfo stripeChargeInfo) {
        return new PaymentInfo(stripeChargeInfo.getChargeAmount());
    }
}