
# Sistema de Gestão de Pagamentos com Padrão State

## Descrição

Este projeto implementa o padrão de projeto **State** para gerenciar o ciclo de vida de pagamentos em um sistema bancário. Ele aborda as diferentes etapas do processamento de pagamentos, como **Pendente**, **Processando**, **Concluído** e **Cancelado**, permitindo que o comportamento do sistema varie conforme o estado atual.

---

## Cenário

O sistema gerencia transações financeiras e precisa tratar diferentes estados de um pagamento:

1. **Pendente**: O pagamento foi criado, mas ainda não processado.
2. **Processando**: O pagamento está em processamento.
3. **Concluído**: O pagamento foi processado com sucesso.
4. **Cancelado**: O pagamento foi cancelado.

Cada estado define comportamentos específicos para as operações de **processar**, **cancelar** e **concluir**.

---

## Componentes do Sistema

1. **PaymentState**: Interface que define o contrato para os estados.
2. **Estados Concretos**: Implementações de cada estado (Pendente, Processando, Concluído, Cancelado).
3. **PaymentContext**: Classe que gerencia o estado atual de um pagamento e delega as operações ao estado correspondente.
4. **PaymentController**: Exposição de uma API REST para gerenciar os pagamentos.

---

## Implementação

### 1. Interface `PaymentState`

Define o contrato para os estados:

```java
public interface PaymentState {
    void process(PaymentContext context);
    void cancel(PaymentContext context);
    void complete(PaymentContext context);
}
```

---

### 2. Estados Concretos

#### Estado **Pendente**

```java
public class PendingState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Pagamento está sendo processado...");
        context.setState(new ProcessingState());
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Pagamento pendente foi cancelado.");
        context.setState(new CanceledState());
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Não é possível concluir um pagamento pendente.");
    }
}
```

#### Estado **Processando**

```java
public class ProcessingState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Pagamento já está sendo processado.");
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Cancelando pagamento em processamento...");
        context.setState(new CanceledState());
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Pagamento processado com sucesso!");
        context.setState(new CompletedState());
    }
}
```

#### Estado **Concluído**

```java
public class CompletedState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Pagamento já foi concluído.");
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Pagamento concluído não pode ser cancelado.");
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Pagamento já está concluído.");
    }
}
```

#### Estado **Cancelado**

```java
public class CanceledState implements PaymentState {
    @Override
    public void process(PaymentContext context) {
        System.out.println("Não é possível processar um pagamento cancelado.");
    }

    @Override
    public void cancel(PaymentContext context) {
        System.out.println("Pagamento já foi cancelado.");
    }

    @Override
    public void complete(PaymentContext context) {
        System.out.println("Não é possível concluir um pagamento cancelado.");
    }
}
```

---

### 3. Contexto do Pagamento

Gerencia o estado atual do pagamento:

```java
public class PaymentContext {
    private PaymentState state;

    public PaymentContext() {
        this.state = new PendingState(); // Estado inicial
    }

    public void setState(PaymentState state) {
        this.state = state;
    }

    public void process() {
        state.process(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void complete() {
        state.complete(this);
    }
}
```

---

### 4. Controlador REST

Expõe a lógica de estados via API REST:

```java
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private Map<String, PaymentContext> payments = new HashMap<>();

    @PostMapping("/{paymentId}/create")
    public ResponseEntity<String> createPayment(@PathVariable String paymentId) {
        payments.put(paymentId, new PaymentContext());
        return ResponseEntity.ok("Pagamento criado com ID: " + paymentId);
    }

    @PostMapping("/{paymentId}/process")
    public ResponseEntity<String> processPayment(@PathVariable String paymentId) {
        PaymentContext context = payments.get(paymentId);
        if (context != null) {
            context.process();
            return ResponseEntity.ok("Pagamento processado.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado.");
    }

    @PostMapping("/{paymentId}/cancel")
    public ResponseEntity<String> cancelPayment(@PathVariable String paymentId) {
        PaymentContext context = payments.get(paymentId);
        if (context != null) {
            context.cancel();
            return ResponseEntity.ok("Pagamento cancelado.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado.");
    }

    @PostMapping("/{paymentId}/complete")
    public ResponseEntity<String> completePayment(@PathVariable String paymentId) {
        PaymentContext context = payments.get(paymentId);
        if (context != null) {
            context.complete();
            return ResponseEntity.ok("Pagamento concluído.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado.");
    }
}
```

---

## Fluxo de Execução

1. Criar um pagamento via `/payments/{paymentId}/create`.
2. Processar o pagamento via `/payments/{paymentId}/process`.
3. Cancelar ou concluir o pagamento via `/payments/{paymentId}/cancel` ou `/payments/{paymentId}/complete`.

---

## Benefícios

1. **Flexibilidade**: Adicionar novos estados, como "Em revisão", é simples.
2. **Responsabilidade Clara**: Cada estado gerencia exclusivamente suas operações.
3. **Reuso**: Estados e comportamentos podem ser reutilizados em outros fluxos.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
