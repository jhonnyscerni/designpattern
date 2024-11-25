package br.com.designpattern.criacionais.prototype;

import java.util.HashMap;
import java.util.Map;

public class AccountPrototypeRegistry {
    private Map<String, BankAccount> prototypes = new HashMap<>();

    public AccountPrototypeRegistry() {
        prototypes.put("savings", new SavingsAccount());
        prototypes.put("checking", new CheckingAccount());
    }

    public BankAccount getPrototype(String type) throws CloneNotSupportedException {
        return prototypes.get(type).clone();
    }
}
