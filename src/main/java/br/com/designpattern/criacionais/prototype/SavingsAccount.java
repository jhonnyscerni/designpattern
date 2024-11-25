package br.com.designpattern.criacionais.prototype;

public class SavingsAccount extends BankAccount {
    public SavingsAccount() {
        this.setAccountType("Savings");
        this.setInterestRate(1.5);
        this.setOverdraftLimit(0.0);
    }
}