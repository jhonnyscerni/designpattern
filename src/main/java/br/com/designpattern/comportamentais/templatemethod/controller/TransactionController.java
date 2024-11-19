package br.com.designpattern.comportamentais.templatemethod.controller;

import br.com.designpattern.comportamentais.templatemethod.service.TransactionService;
import br.com.designpattern.comportamentais.templatemethod.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templatemethod/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     curl -X POST \
     http://localhost:8080/api/transactions/P2P \
     -H "Content-Type: application/json" \
     -d '{
     "id": "12345",
     "amount": 150.00,
     "fee": 0.0
     }'
     */

    @PostMapping("/{type}")
    public ResponseEntity<String> processTransaction(
            @PathVariable String type, @RequestBody Transaction transaction) {
        transactionService.processTransaction(type, transaction);
        return ResponseEntity.ok("Transaction processed successfully!");
    }
}
