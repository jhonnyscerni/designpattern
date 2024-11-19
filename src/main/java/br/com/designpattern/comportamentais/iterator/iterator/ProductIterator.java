package br.com.designpattern.comportamentais.iterator.iterator;

import br.com.designpattern.comportamentais.iterator.model.Product;

public interface ProductIterator {
    boolean hasNext();
    Product next();
}