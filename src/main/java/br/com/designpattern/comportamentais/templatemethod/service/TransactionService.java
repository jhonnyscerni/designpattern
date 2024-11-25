package br.com.designpattern.comportamentais.templatemethod.service;

import br.com.designpattern.comportamentais.templatemethod.model.Transaction;
import br.com.designpattern.comportamentais.templatemethod.processor.TransactionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("templatemethodTransactionService")
public class TransactionService {

    private final Map<String, TransactionProcessor> processors;

    @Autowired
    public TransactionService(List<TransactionProcessor> processorList) {
        // Mapeia processadores pelo tipo
        processors = processorList.stream()
                .collect(Collectors.toMap(p -> p.getClass().getSimpleName(), p -> p));
    }

    public void processTransaction(String transactionType, Transaction transaction) {
        TransactionProcessor processor = processors.get(transactionType + "TransactionProcessor");
        if (processor == null) {
            throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
        }
        processor.processTransaction(transaction);
    }
}
