
# Padrão Prototype em um Sistema Bancário

O padrão **Prototype** é útil para cenários em que você precisa criar novas instâncias de objetos com características já definidas por outros objetos, sem criar dependência direta de suas classes. No setor bancário, esse padrão pode ser extremamente útil devido à necessidade de lidar com objetos complexos e configuráveis, como contas bancárias, perfis de clientes, ou configurações de transações.

## Cenário Real: Implementação do Prototype em um Sistema Bancário

### Contexto
Um engenheiro de software estava trabalhando em um sistema bancário que gerencia diferentes tipos de contas bancárias (**corrente**, **poupança**, **conta empresarial**). Cada tipo de conta tem um conjunto de configurações e regras específicas, como taxas de juros, limites de saque e permissões de overdraft.

### Problema
Criar instâncias totalmente novas para cada conta personalizada exigia configurar manualmente todas as propriedades, aumentando o risco de erros e a complexidade do código. Além disso, a criação direta de objetos específicos poderia gerar dependência do código em classes concretas, dificultando manutenção e extensibilidade.

### Solução
O engenheiro utilizou o padrão Prototype para resolver esse problema. Ele criou um **protótipo** para cada tipo de conta bancária padrão, permitindo clonar essas contas sempre que necessário e ajustá-las conforme as necessidades específicas do cliente.

---

## Implementação em Spring Boot

### 1. Definição do Protótipo
Criou-se uma classe base `BankAccount` que implementa a interface `Cloneable`.

```java
public abstract class BankAccount implements Cloneable {
    private String accountType;
    private double interestRate;
    private double overdraftLimit;

    // Getters e Setters

    @Override
    public BankAccount clone() throws CloneNotSupportedException {
        return (BankAccount) super.clone();
    }
}
```

### 2. Tipos Concretos de Conta
Classes específicas de contas foram criadas, como `SavingsAccount` e `CheckingAccount`.

```java
public class SavingsAccount extends BankAccount {
    public SavingsAccount() {
        this.setAccountType("Savings");
        this.setInterestRate(1.5);
        this.setOverdraftLimit(0.0);
    }
}

public class CheckingAccount extends BankAccount {
    public CheckingAccount() {
        this.setAccountType("Checking");
        this.setInterestRate(0.1);
        this.setOverdraftLimit(500.0);
    }
}
```

### 3. Registro de Protótipos
Os protótipos foram registrados em um **Prototype Registry** para facilitar o acesso.

```java
import java.util.HashMap;
import java.util.Map;

public class AccountPrototypeRegistry {
    private Map<String, BankAccount> prototypes = new HashMap<>();

    public AccountPrototypeRegistry() {
        prototypes.put("savings", new SavingsAccount());
        prototypes.put("checking", new CheckingAccount());
    }

    public BankAccount getPrototype(String type) throws CloneNotSupportedException {
        return prototypes.get(type).clone();
    }
}
```

### 4. Uso no Sistema Bancário
O sistema clona o protótipo correspondente e ajusta os parâmetros necessários.

```java
public class BankService {
    private AccountPrototypeRegistry registry;

    public BankService() {
        registry = new AccountPrototypeRegistry();
    }

    public BankAccount createAccount(String type, double customLimit) throws CloneNotSupportedException {
        BankAccount account = registry.getPrototype(type);
        account.setOverdraftLimit(customLimit);
        return account;
    }
}
```

---

## Benefícios Obtidos
- **Redução de Código Repetitivo**: Não era mais necessário inicializar cada propriedade manualmente.
- **Flexibilidade**: Novos tipos de contas podiam ser adicionados ao sistema apenas criando novos protótipos.
- **Manutenibilidade**: O código ficou desacoplado das implementações concretas das contas.

---

## Conclusão
Este exemplo mostra como o padrão Prototype pode ser aplicado de maneira eficaz em sistemas complexos, como no setor bancário, para reduzir a complexidade e aumentar a flexibilidade do código.
