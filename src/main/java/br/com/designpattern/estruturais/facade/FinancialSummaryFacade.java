package br.com.designpattern.estruturais.facade;

import br.com.designpattern.estruturais.facade.dto.FinancialSummary;
import br.com.designpattern.estruturais.facade.dto.Transaction;
import br.com.designpattern.estruturais.facade.service.BankService;
import br.com.designpattern.estruturais.facade.service.InvestmentService;
import br.com.designpattern.estruturais.facade.service.TransactionService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FinancialSummaryFacade {

    private final InvestmentService investmentService;
    private final BankService bankService;
    private final TransactionService transactionService;

    public FinancialSummaryFacade(InvestmentService investmentService,
                                  BankService bankService,
                                  TransactionService transactionService) {
        this.investmentService = investmentService;
        this.bankService = bankService;
        this.transactionService = transactionService;
    }

    public FinancialSummary getFinancialSummary(String userId) {
        // Recupera o saldo de investimentos
        double investmentBalance = investmentService.getBalance(userId);

        // Recupera o saldo bancário
        double bankBalance = bankService.getBalance(userId);

        // Recupera as transações mais recentes
        List<Transaction> recentTransactions = transactionService.getRecentTransactions(userId);

        // Cria e retorna um objeto FinancialSummary com as informações agregadas
        return new FinancialSummary(investmentBalance, bankBalance, recentTransactions);
    }
}

