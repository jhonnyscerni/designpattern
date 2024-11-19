
# Sistema de Validação de Pedidos com Chain of Responsibility

## Descrição

Este projeto implementa um sistema de validação de pedidos em uma plataforma de e-commerce utilizando o padrão **Chain of Responsibility**. O objetivo é validar pedidos de forma modular e flexível, atendendo a regras como:

- Verificação de estoque
- Validação de pagamento
- Análise antifraude
- Verificação de disponibilidade de entrega

Este design permite uma separação clara de responsabilidades, facilitando a manutenção e escalabilidade do sistema.

---

## Motivação

No passado, validações de pedidos eram implementadas de forma monolítica, em métodos únicos com várias condicionais (`if/else`). Este padrão torna o código difícil de manter e pouco adaptável a mudanças.

Com o padrão **Chain of Responsibility**, cada regra de validação é encapsulada em um handler independente, promovendo modularidade e seguindo o princípio da responsabilidade única.

---

## Arquitetura do Sistema

O sistema é baseado em uma cadeia de responsabilidades, onde cada etapa de validação é implementada como um handler independente.

### Fluxo de Validação

1. **Verificar Estoque**
2. **Validar Pagamento**
3. **Análise Antifraude**
4. **Verificar Entrega**

Cada handler é responsável por uma dessas etapas e decide se a validação continua ou é interrompida.

---

## Implementação

### Interface `PedidoHandler`

Define um contrato comum para os handlers:

```java
public interface PedidoHandler {
    boolean processar(Pedido pedido);
}
```

### Handlers Específicos

#### Verificar Estoque

```java
@Component
public class VerificarEstoqueHandler implements PedidoHandler {

    @Override
    public boolean processar(Pedido pedido) {
        if (!estoqueDisponivel(pedido)) {
            return false; // Falha na verificação de estoque
        }
        return true;
    }

    private boolean estoqueDisponivel(Pedido pedido) {
        // Simulação de verificação de estoque
        return true;
    }
}
```

#### Validar Pagamento

```java
@Component
public class ValidarPagamentoHandler implements PedidoHandler {

    @Override
    public boolean processar(Pedido pedido) {
        if (!pagamentoValido(pedido)) {
            return false; // Falha na validação de pagamento
        }
        return true;
    }

    private boolean pagamentoValido(Pedido pedido) {
        // Simulação de validação de pagamento
        return true;
    }
}
```

#### Outros Handlers

Lógicas similares podem ser aplicadas para análise antifraude e verificação de entrega.

### Serviço de Pedido

Gerencia a cadeia de validação:

```java
@Service
public class PedidoService {

    private final List<PedidoHandler> handlers;

    @Autowired
    public PedidoService(List<PedidoHandler> handlers) {
        this.handlers = handlers;
    }

    public boolean processarPedido(Pedido pedido) {
        for (PedidoHandler handler : handlers) {
            if (!handler.processar(pedido)) {
                return false; // Interrompe se alguma validação falhar
            }
        }
        return true; // Todas as validações passaram
    }
}
```

### Configuração de Ordem dos Handlers (Opcional)

Use `@Order` para definir a ordem de execução dos handlers.

### Controlador de Pedidos

Expõe uma API para processar pedidos:

```java
@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/processar-pedido")
    public ResponseEntity<String> processarPedido(@RequestBody Pedido pedido) {
        boolean pedidoValido = pedidoService.processarPedido(pedido);
        if (!pedidoValido) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pedido inválido");
        }
        return ResponseEntity.ok("Pedido processado com sucesso");
    }
}
```

---

## Benefícios

- **Modularidade**: Handlers independentes facilitam manutenção e extensão.
- **Flexibilidade**: Adicionar, remover ou reorganizar validações é simples.
- **Responsabilidade Única**: Cada handler realiza apenas uma tarefa específica.

---

## Considerações

- **Evolução**: O design é ideal para regras de negócio dinâmicas.
- **Escalabilidade**: Fácil adaptação a novas validações e mudanças de fluxo.

---

## Como Executar

1. Clone o repositório.
2. Configure o ambiente Java/Spring Boot.
3. Compile e execute o projeto.
4. Faça requisições HTTP para o endpoint `/processar-pedido` com um JSON representando o pedido.

---

## Exemplo de JSON para Teste

```json
{
  "id": 1,
  "itens": ["item1", "item2"],
  "pagamento": {
    "metodo": "cartao_credito",
    "valor": 100.00
  },
  "enderecoEntrega": {
    "cidade": "São Paulo",
    "estado": "SP"
  }
}
```

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---