package br.com.designpattern.comportamentais.command.service;

import br.com.designpattern.comportamentais.command.model.TransferRequest;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    public void transferUsingBalance(TransferRequest request) {
        System.out.println("Transferência via saldo: " + request);
        // Lógica de transferência via saldo
    }

    public void transferUsingCredit(TransferRequest request) {
        System.out.println("Transferência via crédito: " + request);
        // Lógica de transferência via crédito
    }
}
