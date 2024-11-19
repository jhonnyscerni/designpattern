package br.com.designpattern.comportamentais.visitor.model;

import br.com.designpattern.comportamentais.visitor.visitor.TransactionVisitor;

public interface VisitableTransaction {
    void accept(TransactionVisitor visitor);
}
