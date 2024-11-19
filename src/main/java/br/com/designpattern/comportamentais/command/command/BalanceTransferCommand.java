package br.com.designpattern.comportamentais.command.command;

import br.com.designpattern.comportamentais.command.model.TransferRequest;
import br.com.designpattern.comportamentais.command.service.TransferService;
import org.springframework.stereotype.Service;

@Service
public class BalanceTransferCommand implements TransferCommand {
    private final TransferService transferService;
    private final TransferRequest request;

    public BalanceTransferCommand(TransferService transferService, TransferRequest request) {
        this.transferService = transferService;
        this.request = request;
    }

    @Override
    public void execute() {
        transferService.transferUsingBalance(request);
    }
}