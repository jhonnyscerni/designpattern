
# Sistema de Notificações com Padrão Observer

## Descrição

Este projeto implementa o padrão de projeto **Observer** para gerenciar notificações em um marketplace. O sistema permite que diferentes partes interessadas (vendedores, compradores, equipe interna) sejam notificadas de eventos relevantes, como criação de pedidos, aprovação de pagamentos, envio de pedidos, ou falhas no pagamento.

---

## Cenário

No contexto de um marketplace, notificações são essenciais para garantir comunicação eficiente entre as partes interessadas:

1. **Vendedores**: Notificados sobre a criação de pedidos e aprovação de pagamentos.
2. **Compradores**: Notificados sobre o envio e entrega dos pedidos.
3. **Equipe Interna**: Notificada sobre falhas no sistema, como problemas de pagamento.

O sistema utiliza o padrão **Observer** para lidar com diferentes tipos de eventos e observadores.

---

## Componentes do Sistema

1. **Event**: Representa um evento com tipo, mensagem e dados associados.
2. **EventListener**: Interface para definir observadores.
3. **NotificationManager**: Gerencia os eventos e os observadores.
4. **Listeners**: Implementações de observadores específicos (vendedores, compradores, equipe interna).
5. **OrderService**: Dispara eventos relacionados a pedidos.
6. **OrderController**: Exposição de endpoints REST para gerar eventos.

---

## Implementação

### 1. Modelo do Evento (`Event`)

Representa os dados de um evento:

```java
public class Event {
    private String type;
    private String message;
    private Object data;

    public Event(String type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
```

---

### 2. Interface `EventListener`

Define o contrato para observadores:

```java
public interface EventListener {
    void update(Event event);
}
```

---

### 3. Gerenciador de Observação (`NotificationManager`)

Centraliza a gestão de eventos e observadores:

```java
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationManager {

    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    public void subscribe(String eventType, EventListener listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        if (users != null) {
            users.remove(listener);
        }
    }

    public void notify(Event event) {
        List<EventListener> users = listeners.get(event.getType());
        if (users != null) {
            for (EventListener listener : users) {
                listener.update(event);
            }
        }
    }
}
```

---

### 4. Implementação de Observadores

#### Vendedor

```java
import org.springframework.stereotype.Component;

@Component
public class SellerNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Seller notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
```

#### Comprador

```java
import org.springframework.stereotype.Component;

@Component
public class BuyerNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Buyer notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
```

#### Equipe Interna

```java
import org.springframework.stereotype.Component;

@Component
public class InternalTeamNotificationListener implements EventListener {
    @Override
    public void update(Event event) {
        System.out.println("Internal team notified: " + event.getMessage() + " | Data: " + event.getData());
    }
}
```

---

### 5. Serviço de Pedido

Dispara eventos para os observadores registrados:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final NotificationManager notificationManager;

    @Autowired
    public OrderService(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public void createOrder(String orderId) {
        System.out.println("Order created with ID: " + orderId);
        Event event = new Event("ORDER_CREATED", "New order created.", orderId);
        notificationManager.notify(event);
    }

    public void approvePayment(String orderId) {
        System.out.println("Payment approved for order ID: " + orderId);
        Event event = new Event("PAYMENT_APPROVED", "Payment approved.", orderId);
        notificationManager.notify(event);
    }

    public void shipOrder(String orderId) {
        System.out.println("Order shipped with ID: " + orderId);
        Event event = new Event("ORDER_SHIPPED", "Order shipped.", orderId);
        notificationManager.notify(event);
    }

    public void paymentFailed(String orderId) {
        System.out.println("Payment failed for order ID: " + orderId);
        Event event = new Event("PAYMENT_FAILED", "Payment failed.", orderId);
        notificationManager.notify(event);
    }
}
```

---

### 6. Controlador REST

Configura assinaturas de eventos e expõe endpoints:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final NotificationManager notificationManager;
    private final SellerNotificationListener sellerListener;
    private final BuyerNotificationListener buyerListener;
    private final InternalTeamNotificationListener internalTeamListener;

    @Autowired
    public OrderController(OrderService orderService, NotificationManager notificationManager,
                           SellerNotificationListener sellerListener,
                           BuyerNotificationListener buyerListener,
                           InternalTeamNotificationListener internalTeamListener) {
        this.orderService = orderService;
        this.notificationManager = notificationManager;
        this.sellerListener = sellerListener;
        this.buyerListener = buyerListener;
        this.internalTeamListener = internalTeamListener;

        // Configurar assinaturas
        notificationManager.subscribe("ORDER_CREATED", sellerListener);
        notificationManager.subscribe("PAYMENT_APPROVED", sellerListener);
        notificationManager.subscribe("ORDER_SHIPPED", buyerListener);
        notificationManager.subscribe("PAYMENT_FAILED", internalTeamListener);
    }

    @GetMapping("/create-order")
    public String createOrder(@RequestParam String orderId) {
        orderService.createOrder(orderId);
        return "Order created!";
    }

    @GetMapping("/approve-payment")
    public String approvePayment(@RequestParam String orderId) {
        orderService.approvePayment(orderId);
        return "Payment approved!";
    }

    @GetMapping("/ship-order")
    public String shipOrder(@RequestParam String orderId) {
        orderService.shipOrder(orderId);
        return "Order shipped!";
    }

    @GetMapping("/fail-payment")
    public String failPayment(@RequestParam String orderId) {
        orderService.paymentFailed(orderId);
        return "Payment failed!";
    }
}
```

---

## Vantagens

- **Modularidade**: Observadores podem ser adicionados ou removidos sem impactar o sistema.
- **Flexibilidade**: Suporte para múltiplos tipos de eventos e notificações.
- **Escalabilidade**: Integração com mecanismos de filas para alta performance (ex.: Kafka, RabbitMQ).

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
