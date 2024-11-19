package br.com.designpattern.comportamentais.iterator.iterator.collection;

import br.com.designpattern.comportamentais.iterator.iterator.ProductIterator;

public interface ProductCollection {
    ProductIterator createIterator();
}