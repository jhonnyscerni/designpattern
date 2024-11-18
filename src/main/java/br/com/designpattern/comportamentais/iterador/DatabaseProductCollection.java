package br.com.designpattern.comportamentais.iterador;

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