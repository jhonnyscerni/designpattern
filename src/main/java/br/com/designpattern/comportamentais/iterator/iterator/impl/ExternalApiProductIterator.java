package br.com.designpattern.comportamentais.iterator.iterator.impl;

import br.com.designpattern.comportamentais.iterator.iterator.ProductIterator;
import br.com.designpattern.comportamentais.iterator.model.Product;

import java.util.Iterator;

public class ExternalApiProductIterator implements ProductIterator {
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
