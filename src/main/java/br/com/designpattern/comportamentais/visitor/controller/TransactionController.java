package br.com.designpattern.comportamentais.visitor.controller;

import br.com.designpattern.comportamentais.visitor.model.VisitableTransaction;
import br.com.designpattern.comportamentais.visitor.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("visitorTransactionController")
@RequestMapping("/visitor/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * curl -X POST http://localhost:8080/transactions/process \
     *      -H "Content-Type: application/json" \
     *      -d '[
     *          {
     *              "type": "PaymentTransaction",
     *              "merchant": "Loja XYZ",
     *              "amount": 120.50
     *          },
     *          {
     *              "type": "TransferTransaction",
     *              "fromAccount": "12345",
     *              "toAccount": "67890",
     *              "amount": 500.00
     *          },
     *          {
     *              "type": "WithdrawalTransaction",
     *              "account": "12345",
     *              "amount": 200.00
     *          }
     *      ]'
     */

    @PostMapping("/process")
    public String processTransaction(@RequestBody VisitableTransaction[] transactions) {
        for (VisitableTransaction transaction : transactions) {
            transactionService.processTransaction(transaction);
        }
        return "Transactions processed successfully!";
    }
}
