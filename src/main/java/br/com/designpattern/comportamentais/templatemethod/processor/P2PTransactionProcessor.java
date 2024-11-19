package br.com.designpattern.comportamentais.templatemethod.processor;

import br.com.designpattern.comportamentais.templatemethod.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class P2PTransactionProcessor extends TransactionProcessor {

    @Override
    protected void validate(Transaction transaction) {
        System.out.println("Validating P2P transaction...");
        // Regras específicas de validação, como saldo suficiente.
    }

    @Override
    protected void calculateFees(Transaction transaction) {
        System.out.println("Calculating fees for P2P transaction...");
        // Implementar lógica de cálculo de taxas P2P
        transaction.setFee(1.50);
    }

    @Override
    protected void authorize(Transaction transaction) {
        System.out.println("Authorizing P2P transaction...");
        // Implementar lógica de autorização, como dupla autenticação.
    }
}
