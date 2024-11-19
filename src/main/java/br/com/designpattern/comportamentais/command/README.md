
# Sistema de Transferência Bancária com Padrão Command

## Descrição

Este projeto demonstra a implementação do padrão de projeto **Command** em um sistema bancário. Ele resolve o problema de executar comandos parametrizáveis, armazená-los para auditoria ou execução futura, além de permitir funcionalidades como **retry** e **undo**.

### Cenário do Problema

No contexto de um sistema bancário, o gerenciamento de transferências financeiras pode incluir:

- Transferências agendadas.
- Retentativas em caso de falhas.
- Diferentes métodos de execução (ex.: saldo, crédito especial).
- Armazenamento de logs para auditoria.

O padrão Command ajuda a desacoplar a lógica de execução da lógica de controle, tornando o sistema flexível e extensível.

---

## Estrutura do Padrão Command

1. **Command Interface**: Define a estrutura de comandos executáveis.
2. **Concrete Commands**: Implementações específicas de comandos.
3. **Invoker**: Classe que dispara comandos (ex.: executor de transferências).
4. **Receiver**: Classe que realiza as operações concretas (ex.: serviço bancário).
5. **Client**: Configura os comandos e inicia o processo.

---

## Implementação

### 1. Interface Command

Define a estrutura de comandos:

```java
public interface TransferCommand {
    void execute();
}
```

### 2. Comandos Concretos

Cada comando encapsula uma lógica de transferência específica:

#### Transferência via Saldo

```java
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
```

#### Transferência via Crédito Especial

```java
@Service
public class CreditTransferCommand implements TransferCommand {
    private final TransferService transferService;
    private final TransferRequest request;

    public CreditTransferCommand(TransferService transferService, TransferRequest request) {
        this.transferService = transferService;
        this.request = request;
    }

    @Override
    public void execute() {
        transferService.transferUsingCredit(request);
    }
}
```

---

### 3. Receiver (Serviço de Transferência)

O `TransferService` implementa as operações concretas de transferência:

```java
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
```

---

### 4. Invoker (Executor de Comandos)

Recebe e executa os comandos:

```java
@Service
public class TransferExecutor {
    public void executeCommand(TransferCommand command) {
        command.execute();
    }
}
```

---

### 5. Client (Controlador REST)

Configura os comandos com base na solicitação e dispara a execução:

```java
@RestController
@RequestMapping("/transfers")
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
```

---

### 6. Modelo da Requisição

Representa os dados necessários para uma transferência:

```java
public class TransferRequest {
    private String type; // BALANCE ou CREDIT
    private String accountFrom;
    private String accountTo;
    private Double amount;

    // Getters e Setters
}
```

---

## Fluxo de Funcionamento

1. O cliente envia uma solicitação para o endpoint `/transfers`.
2. O controlador seleciona o comando concreto com base no tipo de transferência (`BALANCE` ou `CREDIT`).
3. O comando é executado pelo `TransferExecutor`.
4. O `TransferService` realiza a operação.

---

## Benefícios

- **Extensibilidade**: Suporte para novos tipos de transferência, como Pix, sem modificar o código existente.
- **Auditoria**: Comandos podem ser armazenados para conformidade e resiliência.
- **Desacoplamento**: Lógica de controle separada da lógica de execução.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---

## Como Executar

1. Clone o repositório.
2. Configure o ambiente Java/Spring Boot.
3. Compile e execute o projeto.
4. Envie solicitações HTTP para o endpoint `/transfers` com o seguinte formato:

```json
{
  "type": "BALANCE",
  "accountFrom": "123456",
  "accountTo": "654321",
  "amount": 100.00
}
```

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
