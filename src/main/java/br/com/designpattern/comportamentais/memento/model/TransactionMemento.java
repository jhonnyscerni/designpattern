package br.com.designpattern.comportamentais.memento.model;

public class TransactionMemento {
    private final String sender;
    private final String receiver;
    private final Double amount;

    public TransactionMemento(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Double getAmount() {
        return amount;
    }
}
