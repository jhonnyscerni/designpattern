package br.com.designpattern.composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class, args);

        // Criando arquivos individuais
        FileComponent file1 = new FileLeaf("Documento1.txt");
        FileComponent file2 = new FileLeaf("Documento2.txt");

        // Criando uma pasta e adicionando arquivos a ela
        FolderComposite folder1 = new FolderComposite("Pasta1");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        // Criando uma pasta de nível superior e adicionando subpastas
        FolderComposite mainFolder = new FolderComposite("PastaPrincipal");
        mainFolder.addComponent(folder1);

        // Mostrando detalhes da estrutura
        mainFolder.showDetails();
    }
}