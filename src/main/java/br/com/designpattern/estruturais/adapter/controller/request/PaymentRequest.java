package br.com.designpattern.adapter.controller.request;

public class PaymentRequest {
    private String provider;
    private double amount;

    // Getters and setters
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}