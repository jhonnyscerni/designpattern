package br.com.designpattern.comportamentais.iterator.iterator.impl;

import br.com.designpattern.comportamentais.iterator.iterator.ProductIterator;
import br.com.designpattern.comportamentais.iterator.model.Product;

import java.util.List;

public class DatabaseProductIterator implements ProductIterator {
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