
# Padrão Facade para Integração de Serviços Financeiros

## Exemplo Real do Mercado

Um Engenheiro de Software sênior em uma fintech pode implementar o padrão **Facade** ao integrar múltiplos serviços financeiros para oferecer uma interface unificada ao usuário final. 

### Cenário

A funcionalidade de "Resumo Financeiro" precisa combinar dados de:
1. **Investimentos**
2. **Saldo Bancário**
3. **Transações Recentes**

Cada um desses dados é obtido de diferentes APIs internas ou externas. O **Facade** fornece uma maneira unificada de consolidar essas informações.

---

## Estrutura do Exemplo

1. **`InvestmentService`**: Conecta-se a uma API de investimentos para obter o saldo e o desempenho da carteira.
2. **`BankService`**: Recupera o saldo bancário do usuário.
3. **`TransactionService`**: Consulta as transações mais recentes.

---

### Exemplo de `FinancialSummaryFacade`

```java
public class FinancialSummaryFacade {

    private final InvestmentService investmentService;
    private final BankService bankService;
    private final TransactionService transactionService;

    public FinancialSummaryFacade(InvestmentService investmentService,
                                  BankService bankService,
                                  TransactionService transactionService) {
        this.investmentService = investmentService;
        this.bankService = bankService;
        this.transactionService = transactionService;
    }

    public FinancialSummary getFinancialSummary(String userId) {
        double investmentBalance = investmentService.getBalance(userId);
        double bankBalance = bankService.getBalance(userId);
        List<Transaction> recentTransactions = transactionService.getRecentTransactions(userId);

        return new FinancialSummary(investmentBalance, bankBalance, recentTransactions);
    }
}
```

---

## Detalhes dos Serviços

### 1. Serviço de Investimentos: `InvestmentService`

```java
public class InvestmentService {
    public double getBalance(String userId) {
        // Simula o saldo de investimentos
        return 15000.75;
    }
}
```

---

### 2. Serviço Bancário: `BankService`

```java
public class BankService {
    public double getBalance(String userId) {
        // Simula o saldo bancário
        return 5000.25;
    }
}
```

---

### 3. Serviço de Transações: `TransactionService`

```java
import java.util.List;

public class TransactionService {
    public List<Transaction> getRecentTransactions(String userId) {
        // Simula as transações recentes
        return List.of(
            new Transaction("Compra", -50.0),
            new Transaction("Depósito", 200.0),
            new Transaction("Pagamento", -30.0)
        );
    }
}
```

---

## Modelo de Dados Consolidado: `FinancialSummary`

```java
import java.util.List;

public class FinancialSummary {
    private final double investmentBalance;
    private final double bankBalance;
    private final List<Transaction> recentTransactions;

    public FinancialSummary(double investmentBalance, double bankBalance, List<Transaction> recentTransactions) {
        this.investmentBalance = investmentBalance;
        this.bankBalance = bankBalance;
        this.recentTransactions = recentTransactions;
    }

    // Getters e métodos adicionais
}
```

---

## Uso da `FinancialSummaryFacade`

```java
public class Main {
    public static void main(String[] args) {
        InvestmentService investmentService = new InvestmentService();
        BankService bankService = new BankService();
        TransactionService transactionService = new TransactionService();

        FinancialSummaryFacade facade = new FinancialSummaryFacade(investmentService, bankService, transactionService);

        FinancialSummary summary = facade.getFinancialSummary("user123");
        System.out.println("Investment Balance: " + summary.getInvestmentBalance());
        System.out.println("Bank Balance: " + summary.getBankBalance());
        System.out.println("Recent Transactions: " + summary.getRecentTransactions());
    }
}
```

---

## Como Isso Ajuda?

1. **Simplificação**:
   - A `FinancialSummaryFacade` simplifica o processo para o consumidor de obter o resumo financeiro, encapsulando a complexidade das chamadas individuais às APIs.

2. **Facilidade de Manutenção**:
   - Se a empresa alterar a API de investimentos ou a lógica para obter transações, apenas as implementações internas dos serviços específicos precisarão ser modificadas, mantendo a interface do `FinancialSummaryFacade` inalterada.

---

## Benefícios do Padrão Facade

1. **Redução da Complexidade**:
   - O padrão esconde a lógica detalhada e fornece uma interface simplificada.

2. **Centralização**:
   - Agrupa e organiza chamadas a múltiplos serviços.

3. **Escalabilidade**:
   - Facilita a adição de novos serviços no futuro.

O padrão **Facade** é amplamente utilizado para orquestrar chamadas a múltiplos serviços e reduzir a complexidade da aplicação, proporcionando uma interface clara e fácil de usar para outros desenvolvedores e consumidores da API.
