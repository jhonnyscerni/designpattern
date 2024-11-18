package br.com.designpattern.comportamentais.iterador;

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
