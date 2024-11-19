package br.com.designpattern.comportamentais.visitor.visitor;

import br.com.designpattern.comportamentais.visitor.model.impl.TransferTransaction;
import br.com.designpattern.comportamentais.visitor.model.impl.WithdrawalTransaction;
import br.com.designpattern.comportamentais.visitor.model.impl.PaymentTransaction;

public class TransactionProcessorVisitor implements TransactionVisitor {

    @Override
    public void visit(PaymentTransaction payment) {
        // Lógica de processamento para pagamento
        System.out.println("Processando pagamento para o comerciante: " + payment.getMerchant());
        // Validações e processamento específico
    }

    @Override
    public void visit(TransferTransaction transfer) {
        // Lógica de processamento para transferência
        System.out.println("Processando transferência de " + transfer.getFromAccount() +
                " para " + transfer.getToAccount());
        // Validações e processamento específico
    }

    @Override
    public void visit(WithdrawalTransaction withdrawal) {
        // Lógica de processamento para saque
        System.out.println("Processando saque da conta: " + withdrawal.getAccount());
        // Validações e processamento específico
    }
}
