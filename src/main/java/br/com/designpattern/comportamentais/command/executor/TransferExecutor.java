package br.com.designpattern.comportamentais.command.executor;

import br.com.designpattern.comportamentais.command.command.TransferCommand;
import org.springframework.stereotype.Service;

@Service
public class TransferExecutor {
    public void executeCommand(TransferCommand command) {
        command.execute();
    }
}
