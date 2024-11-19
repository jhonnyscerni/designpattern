package br.com.designpattern.comportamentais.templatemethod.processor;

import br.com.designpattern.comportamentais.templatemethod.model.Transaction;

public abstract class TransactionProcessor {

    public void processTransaction(Transaction transaction) {
        validate(transaction);
        calculateFees(transaction);
        authorize(transaction);
        complete(transaction);
    }

    protected abstract void validate(Transaction transaction);

    protected abstract void calculateFees(Transaction transaction);

    protected abstract void authorize(Transaction transaction);

    protected void complete(Transaction transaction) {
        // Passo comum a todas as transações
        System.out.println("Transaction completed for: " + transaction.getId());
    }
}
