
# Padrão Composite em Spring Boot

O padrão de projeto **Composite** é amplamente utilizado em cenários onde é necessário lidar com estruturas hierárquicas, como árvores, permitindo tratar objetos compostos e individuais de forma uniforme. Em sistemas complexos desenvolvidos em **Spring Boot**, o **Composite** pode ser útil, por exemplo, para representar um sistema de permissões ou um sistema de arquivos hierárquico.

---

## Exemplo em Spring Boot usando Composite Pattern

Vamos criar um sistema para representar um **diretório de arquivos**, onde cada arquivo ou pasta pode conter outros arquivos e pastas, simulando a estrutura de um sistema de arquivos.

---

## Estrutura de Classes do Padrão Composite

1. **Componente (Component)**: Interface ou classe abstrata que define o comportamento dos objetos individuais e compostos.
2. **Folha (Leaf)**: Implementação do componente para objetos "indivíduos" (ex.: arquivos).
3. **Composto (Composite)**: Implementação do componente para objetos compostos (ex.: pasta), ou seja, que podem conter outros objetos.

---

### 1. Componente: Interface `FileSystemComponent`

Define o contrato para os objetos do sistema de arquivos:

```java
public interface FileSystemComponent {
    void showDetails();
}
```

---

### 2. Folha: Implementação para Arquivos

Os **arquivos** são elementos terminais que implementam a interface `FileSystemComponent`.

```java
public class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
```

---

### 3. Composto: Implementação para Pastas

As **pastas** podem conter outros arquivos ou pastas.

```java
import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}
```

---

## Implementação no Controlador

No **Spring Boot**, um controlador pode simular a criação e exibição da estrutura do sistema de arquivos:

```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileSystemController {

    @GetMapping("/filesystem")
    public void showFileSystem() {
        FileSystemComponent file1 = new File("Document1.txt");
        FileSystemComponent file2 = new File("Document2.txt");

        Folder folder1 = new Folder("Folder1");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        FileSystemComponent file3 = new File("Image1.png");

        Folder rootFolder = new Folder("RootFolder");
        rootFolder.addComponent(folder1);
        rootFolder.addComponent(file3);

        rootFolder.showDetails();
    }
}
```

---

## Resultado da Execução

Ao acessar o endpoint `/filesystem`, a estrutura do sistema de arquivos será exibida no console:

```plaintext
Folder: RootFolder
  Folder: Folder1
    File: Document1.txt
    File: Document2.txt
  File: Image1.png
```

---

## Benefícios do Padrão Composite

1. **Uniformidade**: Permite tratar objetos individuais e compostos de maneira uniforme.
2. **Extensibilidade**: Novos tipos de componentes podem ser adicionados sem alterar o código existente.
3. **Flexibilidade**: Facilita o gerenciamento de estruturas hierárquicas complexas.

---

Este exemplo mostra como o padrão Composite pode ser aplicado para representar hierarquias de forma simples e elegante, tornando o código mais modular e fácil de manter.
