package br.com.designpattern.comportamentais.command.controller;

import br.com.designpattern.comportamentais.command.command.BalanceTransferCommand;
import br.com.designpattern.comportamentais.command.command.CreditTransferCommand;
import br.com.designpattern.comportamentais.command.command.TransferCommand;
import br.com.designpattern.comportamentais.command.executor.TransferExecutor;
import br.com.designpattern.comportamentais.command.model.TransferRequest;
import br.com.designpattern.comportamentais.command.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command/transfers")
public class TransferController {

    private final TransferService transferService;
    private final TransferExecutor transferExecutor;

    public TransferController(TransferService transferService, TransferExecutor transferExecutor) {
        this.transferService = transferService;
        this.transferExecutor = transferExecutor;
    }

    @PostMapping
    public ResponseEntity<String> executeTransfer(@RequestBody TransferRequest request) {
        TransferCommand command;

        // Aqui podemos utilizar o padrao strategy para selecionar o tipo de comando
        // Seleciona o tipo de comando baseado na entrada
        if ("BALANCE".equalsIgnoreCase(request.getType())) {
            command = new BalanceTransferCommand(transferService, request);
        } else if ("CREDIT".equalsIgnoreCase(request.getType())) {
            command = new CreditTransferCommand(transferService, request);
        } else {
            return ResponseEntity.badRequest().body("Tipo de transferência inválido");
        }

        // Executa o comando
        transferExecutor.executeCommand(command);

        return ResponseEntity.ok("Transferência realizada com sucesso!");
    }
}
