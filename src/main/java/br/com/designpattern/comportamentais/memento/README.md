
# Sistema de Gerenciamento de Transações com Padrão Memento

## Descrição

Este projeto demonstra a aplicação do padrão **Memento** em um sistema bancário, onde usuários podem gerenciar transações financeiras com a opção de desfazer alterações feitas antes da confirmação final. O objetivo é oferecer um meio seguro e eficiente de manipular o estado das transações, permitindo revisões e reversões sem comprometer a integridade dos dados.

---

## Cenário

Em um sistema bancário, usuários frequentemente ajustam valores e destinatários de transações antes de finalizá-las. Este projeto implementa o padrão **Memento** para:

- Salvar estados intermediários de transações.
- Permitir reversões de alterações realizadas.
- Garantir consistência e segurança durante o processo de ajuste.

---

## Componentes do Padrão Memento

1. **TransactionMemento**: Representa o estado salvo de uma transação.
2. **Transaction**: A classe principal que manipula o estado das transações.
3. **TransactionHistory**: Gerencia o histórico de estados (Caretaker).
4. **TransactionController**: Interface REST para os usuários interagirem com o sistema.

---

## Implementação

### 1. Classe `TransactionMemento`

Representa o estado salvo de uma transação:

```java
public class TransactionMemento {
    private final String sender;
    private final String receiver;
    private final Double amount;

    public TransactionMemento(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public Double getAmount() {
        return amount;
    }
}
```

---

### 2. Classe `Transaction`

Manipula o estado da transação:

```java
public class Transaction {
    private String sender;
    private String receiver;
    private Double amount;

    public Transaction(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public void setDetails(String sender, String receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public TransactionMemento save() {
        return new TransactionMemento(sender, receiver, amount);
    }

    public void restore(TransactionMemento memento) {
        this.sender = memento.getSender();
        this.receiver = memento.getReceiver();
        this.amount = memento.getAmount();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + ''' +
                ", receiver='" + receiver + ''' +
                ", amount=" + amount +
                '}';
    }
}
```

---

### 3. Classe `TransactionHistory`

Gerencia o histórico de estados da transação:

```java
import java.util.Stack;

public class TransactionHistory {
    private final Stack<TransactionMemento> history = new Stack<>();

    public void save(Transaction transaction) {
        history.push(transaction.save());
    }

    public void undo(Transaction transaction) {
        if (!history.isEmpty()) {
            transaction.restore(history.pop());
        } else {
            System.out.println("No previous state to restore.");
        }
    }
}
```

---

### 4. Controlador REST

Interface para os usuários interagirem com o sistema:

```java
@RestController
@RequestMapping("/api/transactions")
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
```

---

## Funcionamento

1. O estado atual da transação é salvo antes de qualquer atualização.
2. O usuário pode atualizar os detalhes da transação via `/api/transactions/update`.
3. Caso queira desfazer a última alteração, pode acessar `/api/transactions/undo` para restaurar o estado anterior.
4. A transação atual pode ser visualizada pelo endpoint `/api/transactions`.

---

## Exemplo de Requisições

### Atualizar Transação

```bash
curl -X POST "http://localhost:8080/api/transactions/update?sender=UserC&receiver=UserD&amount=200"
```

**Resposta:**

```json
Transaction updated: Transaction{sender='UserC', receiver='UserD', amount=200.0}
```

### Desfazer Atualização

```bash
curl -X POST "http://localhost:8080/api/transactions/undo"
```

**Resposta:**

```json
Transaction reverted: Transaction{sender='UserA', receiver='UserB', amount=100.0}
```

### Obter Transação Atual

```bash
curl -X GET "http://localhost:8080/api/transactions"
```

**Resposta:**

```json
Current transaction: Transaction{sender='UserA', receiver='UserB', amount=100.0}
```

---

## Benefícios

- **Separação de Responsabilidades**: O padrão Memento separa a lógica de salvamento/restauração de estados da lógica de negócio.
- **Segurança**: Encapsula os detalhes internos do estado da transação.
- **Facilidade de Reversão**: Permite ao usuário desfazer alterações facilmente.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
