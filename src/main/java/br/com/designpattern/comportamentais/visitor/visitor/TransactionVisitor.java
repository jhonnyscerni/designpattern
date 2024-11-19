package br.com.designpattern.comportamentais.visitor.visitor;

import br.com.designpattern.comportamentais.visitor.model.impl.TransferTransaction;
import br.com.designpattern.comportamentais.visitor.model.impl.WithdrawalTransaction;
import br.com.designpattern.comportamentais.visitor.model.impl.PaymentTransaction;

public interface TransactionVisitor {
    void visit(PaymentTransaction payment);
    void visit(TransferTransaction transfer);
    void visit(WithdrawalTransaction withdrawal);
}
