package br.com.designpattern.estruturais.proxy.model;

public class UserAccount {
    private Long userId;
    private String name;
    private String accountNumber;
    private Double balance;

    // Construtor
    public UserAccount(Long userId, String name, String accountNumber, Double balance) {
        this.userId = userId;
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
