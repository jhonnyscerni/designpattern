package br.com.designpattern.comportamentais.iterator.iterator.collection.impl;

import br.com.designpattern.comportamentais.iterator.iterator.collection.ProductCollection;
import br.com.designpattern.comportamentais.iterator.iterator.ProductIterator;
import br.com.designpattern.comportamentais.iterator.iterator.impl.ExternalApiProductIterator;
import br.com.designpattern.comportamentais.iterator.model.Product;

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
