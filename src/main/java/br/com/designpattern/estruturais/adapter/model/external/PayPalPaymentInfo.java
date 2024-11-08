package br.com.designpattern.estruturais.adapter.model.external;

// Estrutura fornecida pela PayPalAPI
public class PayPalPaymentInfo {
    private double paymentAmount;

    public PayPalPaymentInfo(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }
}