package br.com.designpattern.estruturais.facade.service;

import br.com.designpattern.estruturais.facade.dto.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("facadeTransactionService")
public class TransactionService {

    public List<Transaction> getRecentTransactions(String userId) {
        // Simulação de uma chamada para recuperar as transações mais recentes
        System.out.println("Retrieving recent transactions for user " + userId);
        return List.of(
                new Transaction("Purchase at Store A", -100.0),
                new Transaction("Salary", 3000.0),
                new Transaction("Transfer to Savings", -500.0)
        );
    }
}
