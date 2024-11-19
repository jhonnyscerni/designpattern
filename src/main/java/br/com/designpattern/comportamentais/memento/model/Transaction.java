package br.com.designpattern.comportamentais.memento.model;

public class Transaction {
    private String sender;
    private String receiver;
    private Double amount;

    public Transaction(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public void setDetails(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public TransactionMemento save() {
        return new TransactionMemento(sender, receiver, amount);
    }

    public void restore(TransactionMemento memento) {
        this.sender = memento.getSender();
        this.receiver = memento.getReceiver();
        this.amount = memento.getAmount();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                '}';
    }
}
