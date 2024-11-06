package br.com.designpattern.estruturais.facade.service;

import org.springframework.stereotype.Service;

@Service
public class BankService {

    public double getBalance(String userId) {
        // Simulação de uma chamada para recuperar o saldo bancário
        System.out.println("Retrieving bank balance for user " + userId);
        return 5000.0; // Valor fictício de saldo bancário
    }
}
