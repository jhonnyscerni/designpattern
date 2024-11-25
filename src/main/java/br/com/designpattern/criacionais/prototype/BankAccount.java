package br.com.designpattern.criacionais.prototype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BankAccount implements Cloneable {
    private String accountType;
    private double interestRate;
    private double overdraftLimit;

    // Getters e Setters

    @Override
    public BankAccount clone() throws CloneNotSupportedException {
        return (BankAccount) super.clone();
    }
}
