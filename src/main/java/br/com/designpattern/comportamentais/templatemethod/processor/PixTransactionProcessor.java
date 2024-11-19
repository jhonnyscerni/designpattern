package br.com.designpattern.comportamentais.templatemethod.processor;

import br.com.designpattern.comportamentais.templatemethod.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class PixTransactionProcessor extends TransactionProcessor {

    @Override
    protected void validate(Transaction transaction) {
        System.out.println("Validating PIX transaction...");
        // Regras específicas, como validação de chave PIX.
    }

    @Override
    protected void calculateFees(Transaction transaction) {
        System.out.println("Calculating fees for PIX transaction...");
        // Taxas para transações PIX podem ser diferentes.
        transaction.setFee(0.50);
    }

    @Override
    protected void authorize(Transaction transaction) {
        System.out.println("Authorizing PIX transaction...");
        // Autorizações podem incluir verificações adicionais de limites.
    }
}
