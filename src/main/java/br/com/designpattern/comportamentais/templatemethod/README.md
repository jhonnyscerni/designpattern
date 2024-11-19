
Um exemplo realista do uso do padrão Template Method em um setor bancário, poderia envolver a implementação de diferentes regras de validação e processamento de transações financeiras. O Template Method seria usado para definir um fluxo genérico de processamento de transações, com métodos específicos que podem ser personalizados dependendo do tipo de transação (por exemplo, pagamentos P2P, boletos, ou transferências PIX).

Contexto:
No setor bancário, o processamento de transações segue um fluxo comum:

Validação da transação.
Cálculo de taxas.
Autorização.
Conclusão do processamento.
Apesar do fluxo ser o mesmo, os detalhes (como cálculo de taxas e validação) podem variar dependendo do tipo de transação.

O Template Method permite que o fluxo geral seja implementado na classe base, enquanto subclasses personalizam etapas específicas.

Implementação com Spring Boot
1. Definição da Classe Base
```java
public abstract class TransactionProcessor {

    public void processTransaction(Transaction transaction) {
        validate(transaction);
        calculateFees(transaction);
        authorize(transaction);
        complete(transaction);
    }

    protected abstract void validate(Transaction transaction);

    protected abstract void calculateFees(Transaction transaction);

    protected abstract void authorize(Transaction transaction);

    protected void complete(Transaction transaction) {
        // Passo comum a todas as transações
        System.out.println("Transaction completed for: " + transaction.getId());
    }
}
```

2. Subclasses para Tipos Específicos de Transações
Pagamento P2P
```java
@Component
public class P2PTransactionProcessor extends TransactionProcessor {

    @Override
    protected void validate(Transaction transaction) {
        System.out.println("Validating P2P transaction...");
        // Regras específicas de validação, como saldo suficiente.
    }

    @Override
    protected void calculateFees(Transaction transaction) {
        System.out.println("Calculating fees for P2P transaction...");
        // Implementar lógica de cálculo de taxas P2P
        transaction.setFee(1.50);
    }

    @Override
    protected void authorize(Transaction transaction) {
        System.out.println("Authorizing P2P transaction...");
        // Implementar lógica de autorização, como dupla autenticação.
    }
}
```

Transferência PIX
```java
@Component
public class PixTransactionProcessor extends TransactionProcessor {

    @Override
    protected void validate(Transaction transaction) {
        System.out.println("Validating PIX transaction...");
        // Regras específicas, como validação de chave PIX.
    }

    @Override
    protected void calculateFees(Transaction transaction) {
        System.out.println("Calculating fees for PIX transaction...");
        // Taxas para transações PIX podem ser diferentes.
        transaction.setFee(0.50);
    }

    @Override
    protected void authorize(Transaction transaction) {
        System.out.println("Authorizing PIX transaction...");
        // Autorizações podem incluir verificações adicionais de limites.
    }
}
```

3. Serviço para Processamento
Um serviço que escolhe o processador correto com base no tipo de transação.

```java
@Service
public class TransactionService {

    private final Map<String, TransactionProcessor> processors;

    @Autowired
    public TransactionService(List<TransactionProcessor> processorList) {
        // Mapeia processadores pelo tipo
        processors = processorList.stream()
                .collect(Collectors.toMap(p -> p.getClass().getSimpleName(), p -> p));
    }

    public void processTransaction(String transactionType, Transaction transaction) {
        TransactionProcessor processor = processors.get(transactionType + "TransactionProcessor");
        if (processor == null) {
            throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
        }
        processor.processTransaction(transaction);
    }
}
```

4. Entidade de Transação
```java
public class Transaction {
    private String id;
    private double amount;
    private double fee;

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
```

5. Controlador REST para Processamento
```java
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<String> processTransaction(
            @PathVariable String type, @RequestBody Transaction transaction) {
        transactionService.processTransaction(type, transaction);
        return ResponseEntity.ok("Transaction processed successfully!");
    }
}
```

Funcionamento
- Uma requisição HTTP POST para /api/transactions/P2P com os dados da transação executa o fluxo P2P.
- O controlador delega ao serviço, que identifica o processador correto.
- O Template Method organiza o fluxo principal, enquanto cada subclasse personaliza etapas específicas.

Benefícios no Cenário Bancário
- Reutilização de Código: A lógica compartilhada é centralizada na classe base.
- Extensibilidade: Novos tipos de transações podem ser adicionados criando novas subclasses.
- Organização: Cada classe foca apenas na personalização do tipo de transação.

Este exemplo reflete como o Template Method pode ser usado para gerenciar fluxos complexos e garantir consistência em sistemas de pagamentos no setor bancário.
