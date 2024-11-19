
# Integração de Notificações com o Padrão Bridge

No cenário abaixo, temos uma aplicação de notificações que pode enviar mensagens por diferentes meios (ex.: **e-mail** e **SMS**). Podemos ter diferentes tipos de notificações (ex.: **Notificação de alerta** e **Notificação de relatório**). O padrão **Bridge** permite que novos tipos de notificações ou meios de envio sejam adicionados sem alterar todas as combinações possíveis.

---

## Antes do Design Pattern Bridge

Sem o uso do padrão Bridge, a implementação resultaria em uma hierarquia de classes complexa e difícil de manter:

```java
// EmailAlertNotification.java
public class EmailAlertNotification {
    public void send(String message) {
        // Lógica para enviar alerta por e-mail
    }
}

// SmsAlertNotification.java
public class SmsAlertNotification {
    public void send(String message) {
        // Lógica para enviar alerta por SMS
    }
}

// EmailReportNotification.java
public class EmailReportNotification {
    public void send(String message) {
        // Lógica para enviar relatório por e-mail
    }
}

// SmsReportNotification.java
public class SmsReportNotification {
    public void send(String message) {
        // Lógica para enviar relatório por SMS
    }
}
```

Cada combinação de tipo de notificação e método de envio exigia uma nova classe, resultando em uma hierarquia complexa.

---

## Depois do Design Pattern Bridge

Com o padrão **Bridge**, a implementação se torna mais flexível e fácil de manter ao separar a **abstração** (`Notification`) da **implementação** (`MessageSender`).

---

### Abstração

```java
// Notification.java
package br.com.designpattern.bridge;

import br.com.designpattern.bridge.sender.MessageSender;

public abstract class Notification {
    protected MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public abstract void sendNotification(String message);
}
```

---

### Implementação da Abstração

#### Notificação de Alerta

```java
// AlertNotification.java
package br.com.designpattern.bridge.notification;

import br.com.designpattern.bridge.Notification;
import br.com.designpattern.bridge.sender.MessageSender;

public class AlertNotification extends Notification {
    public AlertNotification(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void sendNotification(String message) {
        messageSender.sendMessage("Alert: " + message);
    }
}
```

#### Notificação de Relatório

```java
// ReportNotification.java
package br.com.designpattern.bridge.notification;

import br.com.designpattern.bridge.Notification;
import br.com.designpattern.bridge.sender.MessageSender;

public class ReportNotification extends Notification {
    public ReportNotification(MessageSender messageSender) {
        super(messageSender);
    }

    @Override
    public void sendNotification(String message) {
        messageSender.sendMessage("Report: " + message);
    }
}
```

---

### Implementação do Envio de Mensagens

#### Interface `MessageSender`

```java
// MessageSender.java
package br.com.designpattern.bridge.sender;

public interface MessageSender {
    void sendMessage(String message);
}
```

#### Envio por E-mail

```java
// EmailSender.java
package br.com.designpattern.bridge.sender;

public class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        // Lógica para enviar mensagem por e-mail
        System.out.println("Sending email: " + message);
    }
}
```

#### Envio por SMS

```java
// SmsSender.java
package br.com.designpattern.bridge.sender;

public class SmsSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        // Lógica para enviar mensagem por SMS
        System.out.println("Sending SMS: " + message);
    }
}
```

---

### Uso do Padrão Bridge

```java
// NotificationController.java
package br.com.designpattern.bridge.controller;

import br.com.designpattern.bridge.Notification;
import br.com.designpattern.bridge.notification.AlertNotification;
import br.com.designpattern.bridge.notification.ReportNotification;
import br.com.designpattern.bridge.sender.EmailSender;
import br.com.designpattern.bridge.sender.SmsSender;

public class NotificationController {
    public static void main(String[] args) {
        Notification alertEmail = new AlertNotification(new EmailSender());
        alertEmail.sendNotification("Server is down!");

        Notification reportSms = new ReportNotification(new SmsSender());
        reportSms.sendNotification("Daily report generated.");
    }
}
```

---

## Comparação

1. **Antes**:
   - Cada combinação de tipo de notificação e método de envio exigia uma nova classe.
   - Estrutura rígida e difícil de manter.

2. **Depois**:
   - O padrão **Bridge** separa a abstração (`Notification`) da implementação (`MessageSender`).
   - Permite combinações flexíveis e uma estrutura de código mais limpa e fácil de manter.

---

Com o padrão Bridge, o sistema é escalável e flexível para adicionar novos tipos de notificações ou meios de envio no futuro.
