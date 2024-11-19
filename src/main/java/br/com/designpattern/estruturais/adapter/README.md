
# Integração de Pagamentos com o Padrão Adapter

No cenário bancário, diferentes sistemas de pagamento, como **PayPal** e **Stripe**, enviam informações em formatos distintos e incompatíveis. O padrão de projeto **Adapter** resolve este problema ao traduzir essas estruturas de dados externas em uma forma unificada esperada pela aplicação.

---

## Cenário: Integração sem Interface Comum

- **PayPalAPI** retorna objetos do tipo `PayPalPaymentInfo`.
- **StripeAPI** retorna objetos do tipo `StripeChargeInfo`.

Nossa aplicação, porém, espera uma estrutura padronizada chamada `PaymentInfo`, com um campo comum `amount`.

---

## Solução com Adapter

O **Adapter** converte essas estruturas externas para a forma unificada `PaymentInfo`.

---

### Estrutura Esperada pela Aplicação

```java
public class PaymentInfo {
    private double amount;

    public PaymentInfo(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
```

---

### Estrutura dos Objetos Externos

#### Objeto `PayPalPaymentInfo`

```java
public class PayPalPaymentInfo {
    private double paymentAmount;

    public PayPalPaymentInfo(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }
}
```

#### Objeto `StripeChargeInfo`

```java
public class StripeChargeInfo {
    private double chargeAmount;

    public StripeChargeInfo(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }
}
```

---

### Implementação dos Adaptadores

#### Adaptador PayPal

```java
import org.springframework.stereotype.Component;

@Component
public class PayPalAdapter {

    public PaymentInfo adapt(PayPalPaymentInfo payPalPaymentInfo) {
        return new PaymentInfo(payPalPaymentInfo.getPaymentAmount());
    }
}
```

#### Adaptador Stripe

```java
import org.springframework.stereotype.Component;

@Component
public class StripeAdapter {

    public PaymentInfo adapt(StripeChargeInfo stripeChargeInfo) {
        return new PaymentInfo(stripeChargeInfo.getChargeAmount());
    }
}
```

---

### Serviço de Pagamento com Injeção de Dependência

O **PaymentService** usa os adaptadores para processar os pagamentos de diferentes provedores:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PayPalAdapter payPalAdapter;
    private final StripeAdapter stripeAdapter;

    @Autowired
    public PaymentService(PayPalAdapter payPalAdapter, StripeAdapter stripeAdapter) {
        this.payPalAdapter = payPalAdapter;
        this.stripeAdapter = stripeAdapter;
    }

    public void processPayment(Object paymentInfo) {
        PaymentInfo info;

        if (paymentInfo instanceof PayPalPaymentInfo) {
            info = payPalAdapter.adapt((PayPalPaymentInfo) paymentInfo);
        } else if (paymentInfo instanceof StripeChargeInfo) {
            info = stripeAdapter.adapt((StripeChargeInfo) paymentInfo);
        } else {
            throw new IllegalArgumentException("Tipo de pagamento desconhecido.");
        }

        System.out.println("Processando pagamento de: " + info.getAmount());
    }
}
```

---

### Controlador REST

O **PaymentController** expõe um endpoint HTTP para processar pagamentos:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public String pay(@RequestBody Map<String, Object> request) {
        String provider = (String) request.get("provider");
        double amount = (double) request.get("amount");

        if ("paypal".equalsIgnoreCase(provider)) {
            PayPalPaymentInfo payPalPaymentInfo = new PayPalPaymentInfo(amount);
            paymentService.processPayment(payPalPaymentInfo);
        } else if ("stripe".equalsIgnoreCase(provider)) {
            StripeChargeInfo stripeChargeInfo = new StripeChargeInfo(amount);
            paymentService.processPayment(stripeChargeInfo);
        } else {
            return "Provedor de pagamento desconhecido.";
        }

        return "Pagamento processado com sucesso para o valor: " + amount;
    }
}
```

---

### Testando a API

#### Requisição com PayPal

```bash
curl -X POST http://localhost:8080/pay -H "Content-Type: application/json" -d '{"provider": "paypal", "amount": 150.0}'
```

#### Requisição com Stripe

```bash
curl -X POST http://localhost:8080/pay -H "Content-Type: application/json" -d '{"provider": "stripe", "amount": 200.0}'
```

---

## Explicação

1. **Injeção de Dependência**: O Spring Boot injeta automaticamente os adaptadores no `PaymentService`.
2. **Separação de Responsabilidades**: Cada adaptador converte a estrutura de dados do provedor específico em `PaymentInfo`, o formato padrão esperado.
3. **Escalabilidade**: Novos provedores podem ser adicionados facilmente com novos adaptadores.

Este design torna o sistema flexível e pronto para expandir a integração com novos provedores de pagamento no futuro.
