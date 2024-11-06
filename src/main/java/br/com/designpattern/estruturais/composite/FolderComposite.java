package br.com.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

public class FolderComposite implements FileComponent {
    private String name;
    private List<FileComponent> components = new ArrayList<>();

    public FolderComposite(String name) {
        this.name = name;
    }

    public void addComponent(FileComponent component) {
        components.add(component);
    }

    public void removeComponent(FileComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Pasta: " + name);
        for (FileComponent component : components) {
            component.showDetails();
        }
    }
}
