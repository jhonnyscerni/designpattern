
# Sistema de Revisão de Produtos com Padrão Iterator

## Descrição

Este projeto demonstra a aplicação do padrão **Iterator** em um sistema de e-commerce, onde administradores precisam revisar um catálogo de produtos armazenados em diferentes fontes de dados. O objetivo é unificar o acesso às fontes de dados, independentemente de sua origem, e fornecer uma interface consistente para percorrer os produtos.

---

## Cenário

Em um sistema de e-commerce, produtos podem estar armazenados em:

- Bancos de dados SQL.
- APIs externas.
- Arquivos CSV.

Os administradores precisam acessar todos os produtos de forma transparente, sem se preocupar com a origem dos dados. O padrão **Iterator** resolve este problema ao abstrair a complexidade das fontes de dados, fornecendo um meio unificado de navegação.

---

## Estrutura do Padrão Iterator

1. **Product**: Classe que representa os produtos.
2. **ProductCollection**: Interface para coleções de produtos.
3. **ProductIterator**: Interface do iterador.
4. **ConcreteProductCollections**: Implementações concretas das coleções (ex.: Banco SQL, API externa).
5. **ConcreteProductIterators**: Implementações concretas dos iteradores.
6. **Controller**: Ponto de entrada para os administradores revisarem os produtos.

---

## Implementação

### 1. Classe `Product`

Representa os produtos:

```java
public class Product {
    private String id;
    private String name;
    private Double price;

    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters e toString
}
```

---

### 2. Interface `ProductIterator`

Define a navegação entre os produtos:

```java
public interface ProductIterator {
    boolean hasNext();
    Product next();
}
```

---

### 3. Interface `ProductCollection`

Define a criação de iteradores:

```java
public interface ProductCollection {
    ProductIterator createIterator();
}
```

---

### 4. Implementação para Banco de Dados

```java
import java.util.List;

public class DatabaseProductCollection implements ProductCollection {
    private List<Product> products;

    public DatabaseProductCollection(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductIterator createIterator() {
        return new DatabaseProductIterator(products);
    }
}

class DatabaseProductIterator implements ProductIterator {
    private List<Product> products;
    private int position = 0;

    public DatabaseProductIterator(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean hasNext() {
        return position < products.size();
    }

    @Override
    public Product next() {
        return hasNext() ? products.get(position++) : null;
    }
}
```

---

### 5. Implementação para API Externa

```java
import java.util.Iterator;
import java.util.List;

public class ExternalApiProductCollection implements ProductCollection {
    private List<Product> products;

    public ExternalApiProductCollection(List<Product> products) {
        this.products = products;
    }

    @Override
    public ProductIterator createIterator() {
        return new ExternalApiProductIterator(products.iterator());
    }
}

class ExternalApiProductIterator implements ProductIterator {
    private Iterator<Product> iterator;

    public ExternalApiProductIterator(Iterator<Product> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Product next() {
        return iterator.hasNext() ? iterator.next() : null;
    }
}
```

---

### 6. Controller para Unificar as Coleções

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        // Simula produtos de diferentes fontes
        List<Product> databaseProducts = Arrays.asList(
                new Product("1", "Laptop", 2000.0),
                new Product("2", "Mouse", 50.0)
        );

        List<Product> apiProducts = Arrays.asList(
                new Product("3", "Keyboard", 150.0),
                new Product("4", "Monitor", 300.0)
        );

        ProductCollection dbCollection = new DatabaseProductCollection(databaseProducts);
        ProductCollection apiCollection = new ExternalApiProductCollection(apiProducts);

        List<Product> allProducts = new ArrayList<>();

        ProductIterator dbIterator = dbCollection.createIterator();
        while (dbIterator.hasNext()) {
            allProducts.add(dbIterator.next());
        }

        ProductIterator apiIterator = apiCollection.createIterator();
        while (apiIterator.hasNext()) {
            allProducts.add(apiIterator.next());
        }

        return allProducts;
    }
}
```

---

## Resultado da API

Ao acessar o endpoint `/products`, o administrador recebe uma lista unificada de produtos:

```json
[
    { "id": "1", "name": "Laptop", "price": 2000.0 },
    { "id": "2", "name": "Mouse", "price": 50.0 },
    { "id": "3", "name": "Keyboard", "price": 150.0 },
    { "id": "4", "name": "Monitor", "price": 300.0 }
]
```

---

## Vantagens

- **Abstração**: Os administradores não precisam conhecer a origem dos dados.
- **Facilidade de Extensão**: Novas fontes de dados podem ser integradas facilmente.
- **Reutilização de Código**: A lógica de iteração é separada da lógica das coleções.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**

---

## Licença

Este projeto é distribuído sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
