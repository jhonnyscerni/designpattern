package br.com.designpattern.criacionais.prototype;

public class CheckingAccount extends BankAccount {
    public CheckingAccount() {
        this.setAccountType("Checking");
        this.setInterestRate(0.1);
        this.setOverdraftLimit(500.0);
    }
}