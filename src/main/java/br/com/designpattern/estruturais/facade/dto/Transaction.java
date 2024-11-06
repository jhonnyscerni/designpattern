package br.com.designpattern.estruturais.facade.dto;

public class Transaction {
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getters e setters
}
