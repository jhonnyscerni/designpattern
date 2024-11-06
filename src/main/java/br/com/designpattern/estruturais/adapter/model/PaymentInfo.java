package br.com.designpattern.estruturais.adapter.model;

public class PaymentInfo {
    private double amount;

    public PaymentInfo(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
