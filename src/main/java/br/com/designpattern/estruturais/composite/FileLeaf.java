package br.com.designpattern.composite;

public class FileLeaf implements FileComponent {
    private String name;

    public FileLeaf(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Arquivo: " + name);
    }
}
