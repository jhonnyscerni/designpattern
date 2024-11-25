package br.com.designpattern.comportamentais.memento.controller;

import br.com.designpattern.comportamentais.memento.model.Transaction;
import br.com.designpattern.comportamentais.memento.service.TransactionHistory;
import org.springframework.web.bind.annotation.*;

@RestController("mementoTransactionController")
@RequestMapping("/memento/api/transactions")
public class TransactionController {

    private final Transaction transaction = new Transaction("UserA", "UserB", 100.0);
    private final TransactionHistory transactionHistory = new TransactionHistory();

    @PostMapping("/update")
    public String updateTransaction(@RequestParam String sender,
                                    @RequestParam String receiver,
                                    @RequestParam Double amount) {
        transactionHistory.save(transaction); // Salva o estado atual
        transaction.setDetails(sender, receiver, amount);
        return "Transaction updated: " + transaction.toString();
    }

    @PostMapping("/undo")
    public String undoTransaction() {
        transactionHistory.undo(transaction);
        return "Transaction reverted: " + transaction.toString();
    }

    @GetMapping
    public String getTransaction() {
        return "Current transaction: " + transaction.toString();
    }
}

