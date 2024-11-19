package br.com.designpattern.comportamentais.visitor.service;

import br.com.designpattern.comportamentais.visitor.model.VisitableTransaction;
import br.com.designpattern.comportamentais.visitor.visitor.TransactionProcessorVisitor;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private TransactionProcessorVisitor processor = new TransactionProcessorVisitor();

    public void processTransaction(VisitableTransaction transaction) {
        transaction.accept(processor);
    }
}
