
# Sistema de Cálculo de Juros com Padrão Strategy

## Descrição

Este projeto implementa o padrão de projeto **Strategy** para calcular taxas de juros dinâmicas com base no perfil de clientes. O objetivo é permitir a troca de algoritmos de cálculo de forma flexível e sem impacto no código existente, utilizando as facilidades do Spring Boot.

---

## Cenário

Uma fintech precisa calcular taxas de juros para empréstimos, baseando-se no perfil de risco dos clientes:

1. **Clientes de Alto Risco**: Pagam taxas de juros mais altas.
2. **Clientes de Baixo Risco**: Pagam taxas de juros mais baixas.

O sistema deve permitir a adição de novos algoritmos para perfis diferentes de clientes sem impactar o restante do código.

---

## Componentes do Sistema

1. **TaxaJurosStrategy**: Interface que define o comportamento comum para as estratégias de cálculo de juros.
2. **Estratégias Concretas**: Implementações específicas para cada perfil de cliente.
3. **CalculadoraTaxaJurosService**: Classe que seleciona e aplica a estratégia correta com base no perfil do cliente.
4. **EmprestimoController**: Exposição de uma API REST para acessar o cálculo de juros.

---

## Implementação

### 1. Interface `TaxaJurosStrategy`

Define o contrato para todas as estratégias:

```java
public interface TaxaJurosStrategy {
    BigDecimal calcularTaxa(BigDecimal valorEmprestimo, int prazo);
}
```

---

### 2. Estratégias Concretas

#### Estratégia para **Clientes de Alto Risco**

```java
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("taxaAltaRisco")
public class TaxaJurosAltaRisco implements TaxaJurosStrategy {

    @Override
    public BigDecimal calcularTaxa(BigDecimal valorEmprestimo, int prazo) {
        return valorEmprestimo.multiply(new BigDecimal("0.15")).multiply(new BigDecimal(prazo));
    }
}
```

#### Estratégia para **Clientes de Baixo Risco**

```java
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("taxaBaixoRisco")
public class TaxaJurosBaixoRisco implements TaxaJurosStrategy {

    @Override
    public BigDecimal calcularTaxa(BigDecimal valorEmprestimo, int prazo) {
        return valorEmprestimo.multiply(new BigDecimal("0.05")).multiply(new BigDecimal(prazo));
    }
}
```

---

### 3. Serviço `CalculadoraTaxaJurosService`

Seleciona e aplica a estratégia correta:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class CalculadoraTaxaJurosService {

    private final Map<String, TaxaJurosStrategy> strategies;

    @Autowired
    public CalculadoraTaxaJurosService(Map<String, TaxaJurosStrategy> strategies) {
        this.strategies = strategies;
    }

    public BigDecimal calcularTaxa(String perfilCliente, BigDecimal valorEmprestimo, int prazo) {
        TaxaJurosStrategy strategy = strategies.get(perfilCliente);
        if (strategy == null) {
            throw new IllegalArgumentException("Perfil de cliente desconhecido");
        }
        return strategy.calcularTaxa(valorEmprestimo, prazo);
    }
}
```

---

### 4. Controlador REST

Expõe uma API para o cálculo de juros:

```java
@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final CalculadoraTaxaJurosService calculadoraTaxaJurosService;

    @Autowired
    public EmprestimoController(CalculadoraTaxaJurosService calculadoraTaxaJurosService) {
        this.calculadoraTaxaJurosService = calculadoraTaxaJurosService;
    }

    @GetMapping("/calcular")
    public BigDecimal calcularTaxa(@RequestParam String perfilCliente,
                                   @RequestParam BigDecimal valorEmprestimo,
                                   @RequestParam int prazo) {
        return calculadoraTaxaJurosService.calcularTaxa(perfilCliente, valorEmprestimo, prazo);
    }
}
```

---

## Como Fazer Requisições

Para calcular a taxa de juros, envie uma requisição para o endpoint `/api/emprestimos/calcular`.

### Exemplo de Requisição

```bash
curl -X GET "http://localhost:8080/api/emprestimos/calcular"      -G      --data-urlencode "perfilCliente=taxaAltaRisco"      --data-urlencode "valorEmprestimo=10000"      --data-urlencode "prazo=12"
```

### Parâmetros

- **perfilCliente**: Define o perfil do cliente (ex.: `taxaAltaRisco`, `taxaBaixoRisco`).
- **valorEmprestimo**: Valor do empréstimo solicitado.
- **prazo**: Prazo do empréstimo em meses.

---

### Resposta Esperada

```json
{
  "taxaCalculada": "18000.00"
}
```

---

## Benefícios

1. **Flexibilidade**: Novos cálculos de juros podem ser adicionados sem modificar o código existente.
2. **Manutenção Simplificada**: Cada estratégia está encapsulada em sua própria classe.
3. **Desacoplamento**: O serviço principal não precisa conhecer os detalhes dos algoritmos.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
