package br.com.designpattern.comportamentais.memento.service;

import br.com.designpattern.comportamentais.memento.model.Transaction;
import br.com.designpattern.comportamentais.memento.model.TransactionMemento;

import java.util.Stack;

public class TransactionHistory {
    private final Stack<TransactionMemento> history = new Stack<>();

    public void save(Transaction transaction) {
        history.push(transaction.save());
    }

    public void undo(Transaction transaction) {
        if (!history.isEmpty()) {
            transaction.restore(history.pop());
        } else {
            System.out.println("No previous state to restore.");
        }
    }
}
