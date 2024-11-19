
# Padrão Proxy em Spring Boot

O padrão **Proxy** é usado para fornecer uma camada adicional entre o cliente e o serviço real. Ele é útil para adicionar funcionalidades como controle de acesso, auditoria ou caching sem modificar diretamente o código do serviço principal.

---

## Exemplo: Controle de Acesso e Auditoria com Proxy

Imagine um **serviço bancário** que retorna informações de contas de usuários. Como essas informações são sensíveis, é necessário garantir que:
1. Apenas **usuários autorizados** tenham acesso.
2. Todo acesso seja **registrado** para auditoria.

O **Proxy** é implementado para adicionar essas verificações e funcionalidades sem modificar o serviço principal.

---

## Estrutura do Código

### 1. Interface de Serviço: `UserService`

Define os métodos para acessar os dados do usuário.

```java
public interface UserService {
    UserAccount getUserAccount(Long userId);
}
```

---

### 2. Serviço Real: `UserServiceImpl`

Implementa a lógica principal para obter informações do usuário.

```java
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserAccount getUserAccount(Long userId) {
        // Simula a busca no banco de dados
        return new UserAccount(userId, "John Doe", "1234-5678-9000", 1500.00);
    }
}
```

---

### 3. Proxy: `UserServiceProxy`

Adiciona verificação de permissões e auditoria antes de chamar o serviço real.

```java
@Service
public class UserServiceProxy implements UserService {
    private final UserServiceImpl userService;
    private final AuditService auditService;

    public UserServiceProxy(UserServiceImpl userService, AuditService auditService) {
        this.userService = userService;
        this.auditService = auditService;
    }

    @Override
    public UserAccount getUserAccount(Long userId) {
        // Verificação de permissão
        if (!hasPermission(userId)) {
            throw new SecurityException("Acesso negado para o usuário: " + userId);
        }
        
        // Registro de auditoria
        auditService.logAccess(userId);

        // Chamada ao serviço real
        return userService.getUserAccount(userId);
    }

    private boolean hasPermission(Long userId) {
        // Lógica de permissão (exemplo simplificado)
        return true;
    }
}
```

---

### 4. Serviço de Auditoria: `AuditService`

Registra acessos para fins de auditoria.

```java
@Service
public class AuditService {
    public void logAccess(Long userId) {
        System.out.println("Auditando acesso ao usuário: " + userId + " em " + LocalDateTime.now());
        // Registrar no banco ou sistema de auditoria
    }
}
```

---

### 5. Controlador REST: `UserController`

Usa o **Proxy** em vez do serviço real para incluir verificações e auditoria.

```java
@RestController
@RequestMapping("/api/accounts")
public class UserController {
    private final UserService userService;

    public UserController(UserServiceProxy userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserAccount(userId));
    }
}
```

---

## Funcionamento

1. O cliente faz uma requisição ao endpoint `/api/accounts/{userId}`.
2. O **Proxy (`UserServiceProxy`)**:
   - Verifica se o usuário tem permissão.
   - Registra o acesso via `AuditService`.
   - Chama o serviço real (`UserServiceImpl`) para obter os dados.

---

## Benefícios do Proxy

1. **Segurança**:
   - Adiciona controles de acesso sem alterar a lógica principal.
2. **Auditoria**:
   - Registra acessos de forma centralizada.
3. **Manutenção**:
   - Centraliza funcionalidades adicionais, facilitando alterações futuras.

---

O padrão **Proxy** é ideal para cenários que exigem extensões como controle de acesso, auditoria ou caching, mantendo a lógica do serviço real isolada e fácil de manter.
