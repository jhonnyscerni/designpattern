package br.com.designpattern.comportamentais.iterator.iterator.collection.impl;

import br.com.designpattern.comportamentais.iterator.iterator.collection.ProductCollection;
import br.com.designpattern.comportamentais.iterator.iterator.ProductIterator;
import br.com.designpattern.comportamentais.iterator.iterator.impl.DatabaseProductIterator;
import br.com.designpattern.comportamentais.iterator.model.Product;

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