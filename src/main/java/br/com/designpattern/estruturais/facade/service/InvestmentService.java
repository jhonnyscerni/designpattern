package br.com.designpattern.estruturais.facade.service;

import org.springframework.stereotype.Service;

@Service
public class InvestmentService {

    public double getBalance(String userId) {
        // Simulação de uma chamada para recuperar o saldo de investimentos
        System.out.println("Retrieving investment balance for user " + userId);
        return 10000.0; // Valor fictício de saldo em investimentos
    }
}
