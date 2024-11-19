
# Padrão Decorator para Sistema de Descontos Dinâmicos

Imagine um cenário onde um Engenheiro de Software está trabalhando em um sistema de recomendação para uma plataforma de e-commerce. É necessário um mecanismo flexível para calcular preços dinâmicos com base em diferentes regras de desconto e promoções. As condições de desconto mudam frequentemente devido a campanhas de marketing, preferências de clientes e outros fatores.

O padrão **Decorator** permite aplicar descontos de forma dinâmica e combinar múltiplos descontos sem alterar a lógica principal do cálculo de preços.

---

## Aplicação Prática

### Exemplos de Descontos:

1. **Desconto para Primeiros Usuários**: Aplica um desconto inicial para novos clientes.
2. **Desconto Sazonal**: Aplica descontos em eventos específicos (ex.: Black Friday, Natal).
3. **Desconto por Pontos de Fidelidade**: Reduz o preço com base nos pontos acumulados pelo cliente.

---

## Estrutura do Código

1. **`PriceService`**: Interface base para o cálculo de preços.
2. **`BasicPriceService`**: Implementação do cálculo básico de preços, retornando o valor sem descontos.
3. **Decoradores**:
   - **`NewUserDiscountDecorator`**: Aplica desconto para novos usuários.
   - **`SeasonalDiscountDecorator`**: Adiciona desconto sazonal.
   - **`LoyaltyDiscountDecorator`**: Oferece desconto baseado em pontos de fidelidade.
4. **`DiscountedPriceService`**: Serviço que combina decoradores dinamicamente.
5. **Controller**: Expõe uma API REST para calcular o preço final com os descontos.

---

### Código

#### 1. Interface `PriceService`

```java
public interface PriceService {
    double calculatePrice(double basePrice);
}
```

---

#### 2. Implementação Base: `BasicPriceService`

```java
public class BasicPriceService implements PriceService {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice; // Sem descontos
    }
}
```

---

#### 3. Decorador Base: `PriceDecorator`

```java
public abstract class PriceDecorator implements PriceService {
    protected PriceService wrappedService;

    public PriceDecorator(PriceService wrappedService) {
        this.wrappedService = wrappedService;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return wrappedService.calculatePrice(basePrice);
    }
}
```

---

#### 4. Implementação dos Decoradores

**Desconto para Novos Usuários:**

```java
public class NewUserDiscountDecorator extends PriceDecorator {
    public NewUserDiscountDecorator(PriceService wrappedService) {
        super(wrappedService);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        return price * 0.9; // 10% de desconto
    }
}
```

**Desconto Sazonal:**

```java
public class SeasonalDiscountDecorator extends PriceDecorator {
    public SeasonalDiscountDecorator(PriceService wrappedService) {
        super(wrappedService);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        return price * 0.8; // 20% de desconto
    }
}
```

**Desconto por Fidelidade:**

```java
public class LoyaltyDiscountDecorator extends PriceDecorator {
    public LoyaltyDiscountDecorator(PriceService wrappedService) {
        super(wrappedService);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        return price * 0.95; // 5% de desconto
    }
}
```

---

#### 5. Controller REST

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    @GetMapping("/price/calculate")
    public double calculatePrice(
        @RequestParam double basePrice,
        @RequestParam boolean isNewUser,
        @RequestParam boolean isSeasonal,
        @RequestParam boolean isLoyalCustomer
    ) {
        PriceService priceService = new BasicPriceService();

        if (isNewUser) {
            priceService = new NewUserDiscountDecorator(priceService);
        }
        if (isSeasonal) {
            priceService = new SeasonalDiscountDecorator(priceService);
        }
        if (isLoyalCustomer) {
            priceService = new LoyaltyDiscountDecorator(priceService);
        }

        return priceService.calculatePrice(basePrice);
    }
}
```

---

## Como Usar

### Exemplo de Requisição

```bash
curl -X GET "http://localhost:8080/price/calculate?basePrice=100&isNewUser=true&isSeasonal=true&isLoyalCustomer=true"
```

**Resultado:** O sistema aplica descontos para novos usuários, promoções sazonais e clientes fiéis, retornando o preço final calculado.

---

## Funcionamento do `PriceDecorator`

1. **Classe Abstrata**: `PriceDecorator` serve como base para outros decoradores que implementam descontos específicos.
2. **Encapsulamento**: Permite que múltiplos decoradores sejam encadeados para aplicar descontos diferentes.
3. **Extensibilidade**: Novos tipos de descontos podem ser adicionados facilmente criando novas classes que estendem `PriceDecorator`.

---

## Benefícios do Padrão Decorator

1. **Flexibilidade**: Permite a adição dinâmica de funcionalidades (descontos) ao objeto base.
2. **Reutilização de Código**: Decoradores são modulares e podem ser reutilizados em diferentes combinações.
3. **Manutenção Simples**: Adicionar novos descontos não impacta os existentes.

Este exemplo mostra como o padrão **Decorator** ajuda a criar sistemas flexíveis e escaláveis para aplicações e-commerce.
