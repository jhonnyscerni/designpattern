# Implementação do Padrão Mediator com Spring Boot

Este repositório contém um exemplo prático de implementação do padrão de projeto **Mediator** em um cenário real utilizando **Spring Boot**. O objetivo é demonstrar como reduzir o acoplamento entre componentes em um sistema de reserva de voos.

---

## Contexto do Problema

Sem o padrão **Mediator**, os componentes do sistema comunicariam diretamente uns com os outros, resultando em alto acoplamento e dificuldades de manutenção. Por exemplo:
- O componente de disponibilidade de assentos deve notificar o componente de pagamento.
- O componente de pagamento precisa informar o módulo de notificação sobre o status do pagamento.

Essa abordagem cria dependências difíceis de gerenciar e testar. Utilizando o padrão **Mediator**, os componentes comunicam-se exclusivamente por meio de um mediador central.

---

## Solução: Mediator

A solução utiliza um mediador central que coordena as interações entre os componentes de forma desacoplada.

---

### Implementação

#### 1. **Interface `Component`**
Define o contrato para todos os componentes que interagem com o Mediator.

```java
public interface Component {
    void setMediator(FlightBookingMediator mediator);
}
```

#### 2. **Interface do Mediator**
Define os métodos que o Mediator deve implementar para coordenar os componentes.

```java
public interface FlightBookingMediator {
    void notify(Component sender, String event);
}
```

#### 3. **Implementação do Mediator**
Gerencia as interações entre os componentes.

```java
import org.springframework.stereotype.Component;

@Component
public class FlightBookingMediatorImpl implements FlightBookingMediator {

    private AvailabilityService availabilityService;
    private PaymentService paymentService;
    private NotificationService notificationService;

    public void registerComponents(AvailabilityService availabilityService,
                                   PaymentService paymentService,
                                   NotificationService notificationService) {
        this.availabilityService = availabilityService;
        this.paymentService = paymentService;
        this.notificationService = notificationService;

        this.availabilityService.setMediator(this);
        this.paymentService.setMediator(this);
        this.notificationService.setMediator(this);
    }

    @Override
    public void notify(Component sender, String event) {
        if (event.equals("seatReserved")) {
            System.out.println("Mediator: Seat reserved, triggering payment...");
            paymentService.processPayment();
        } else if (event.equals("paymentProcessed")) {
            System.out.println("Mediator: Payment processed, triggering notification...");
            notificationService.sendNotification();
        }
    }
}
```

#### 4. **Implementação dos Componentes**

##### 4.1. `AvailabilityService`
Gerencia a disponibilidade de assentos e notifica o Mediator.

```java
import org.springframework.stereotype.Service;

@Service
public class AvailabilityService implements Component {

    private FlightBookingMediator mediator;

    @Override
    public void setMediator(FlightBookingMediator mediator) {
        this.mediator = mediator;
    }

    public void reserveSeat() {
        System.out.println("AvailabilityService: Reserving seat...");
        mediator.notify(this, "seatReserved");
    }
}
```

##### 4.2. `PaymentService`
Processa o pagamento quando notificado pelo Mediator.

```java
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements Component {

    private FlightBookingMediator mediator;

    @Override
    public void setMediator(FlightBookingMediator mediator) {
        this.mediator = mediator;
    }

    public void processPayment() {
        System.out.println("PaymentService: Processing payment...");
        mediator.notify(this, "paymentProcessed");
    }
}
```

##### 4.3. `NotificationService`
Envia uma notificação ao cliente após o pagamento.

```java
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements Component {

    private FlightBookingMediator mediator;

    @Override
    public void setMediator(FlightBookingMediator mediator) {
        this.mediator = mediator;
    }

    public void sendNotification() {
        System.out.println("NotificationService: Sending notification to customer...");
    }
}
```

#### 5. **Controlador**
O controlador inicia o fluxo chamando o método de reserva no `AvailabilityService`. O Mediator coordena o restante das ações.

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final FlightBookingMediatorImpl mediator;
    private final AvailabilityService availabilityService;

    public BookingController(FlightBookingMediatorImpl mediator, 
                             AvailabilityService availabilityService,
                             PaymentService paymentService, 
                             NotificationService notificationService) {
        this.mediator = mediator;
        this.availabilityService = availabilityService;

        // Registra os componentes no Mediator
        mediator.registerComponents(availabilityService, paymentService, notificationService);
    }

    @GetMapping("/book-flight")
    public String bookFlight() {
        availabilityService.reserveSeat();
        return "Flight booking initiated!";
    }
}
```

---

## Fluxo de Execução

1. Quando o endpoint `/book-flight` é acessado:
    - O `BookingController` chama o método `reserveSeat()` no `AvailabilityService`.
    - O `AvailabilityService` notifica o `FlightBookingMediator` com o evento `"seatReserved"`.
    - O Mediator coordena e chama o método `processPayment()` no `PaymentService`.
    - Após o pagamento, o `PaymentService` notifica o Mediator com o evento `"paymentProcessed"`.
    - O Mediator chama o método `sendNotification()` no `NotificationService`.

---

## Saída Esperada

Ao acessar o endpoint `/book-flight`, a saída no console será:

```plaintext
AvailabilityService: Reserving seat...
Mediator: Seat reserved, triggering payment...
PaymentService: Processing payment...
Mediator: Payment processed, triggering notification...
NotificationService: Sending notification to customer...
```

O navegador retornará:

```plaintext
Flight booking initiated!
```

---

## Vantagens da Solução

1. **Desacoplamento**:
    - Cada serviço é independente e comunica-se apenas através do Mediator.

2. **Escalabilidade**:
    - Novos componentes podem ser adicionados facilmente sem modificar os existentes.

3. **Manutenção**:
    - Alterações no fluxo de comunicação entre componentes são feitas apenas no Mediator.

---

## Aplicação Prática

Essa abordagem pode ser usada em sistemas de:
- **E-commerce**: Coordenação entre estoque, pagamento e envio.
- **Reservas**: Hotéis, voos ou eventos.
- **Financeiro**: Coordenação de pagamentos, validações e notificações.

A solução é modular e pode ser adaptada para cenários mais complexos.
