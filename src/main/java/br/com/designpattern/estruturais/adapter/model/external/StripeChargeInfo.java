package br.com.designpattern.estruturais.adapter.model.external;

// Estrutura fornecida pela StripeAPI
public class StripeChargeInfo {
    private double chargeAmount;

    public StripeChargeInfo(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }
}