package br.com.designpattern.estruturais.facade.dto;

import java.util.List;

public class FinancialSummary {

    private double investmentBalance;
    private double bankBalance;
    private List<Transaction> recentTransactions;

    public FinancialSummary(double investmentBalance, double bankBalance, List<Transaction> recentTransactions) {
        this.investmentBalance = investmentBalance;
        this.bankBalance = bankBalance;
        this.recentTransactions = recentTransactions;
    }

    // Getters e Setters

    public double getInvestmentBalance() {
        return investmentBalance;
    }

    public void setInvestmentBalance(double investmentBalance) {
        this.investmentBalance = investmentBalance;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public List<Transaction> getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(List<Transaction> recentTransactions) {
        this.recentTransactions = recentTransactions;
    }

    @Override
    public String toString() {
        return "FinancialSummary{" +
                "investmentBalance=" + investmentBalance +
                ", bankBalance=" + bankBalance +
                ", recentTransactions=" + recentTransactions +
                '}';
    }
}
