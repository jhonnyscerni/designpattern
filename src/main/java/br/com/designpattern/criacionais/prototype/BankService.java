package br.com.designpattern.criacionais.prototype;

public class BankService {
    private AccountPrototypeRegistry registry;

    public BankService() {
        registry = new AccountPrototypeRegistry();
    }

    public BankAccount createAccount(String type, double customLimit) throws CloneNotSupportedException {
        BankAccount account = registry.getPrototype(type);
        account.setOverdraftLimit(customLimit);
        return account;
    }
}
