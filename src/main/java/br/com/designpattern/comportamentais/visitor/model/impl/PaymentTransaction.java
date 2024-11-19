package br.com.designpattern.comportamentais.visitor.model.impl;

import br.com.designpattern.comportamentais.visitor.model.VisitableTransaction;
import br.com.designpattern.comportamentais.visitor.visitor.TransactionVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransaction implements VisitableTransaction {
    private String merchant;
    private double amount;

    @Override
    public void accept(TransactionVisitor visitor) {
        visitor.visit(this);
    }
}
