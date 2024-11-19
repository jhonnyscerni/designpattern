package br.com.designpattern.comportamentais.iterator.service;


import br.com.designpattern.comportamentais.iterator.iterator.collection.impl.DatabaseProductCollection;
import br.com.designpattern.comportamentais.iterator.iterator.collection.impl.ExternalApiProductCollection;
import br.com.designpattern.comportamentais.iterator.iterator.collection.ProductCollection;
import br.com.designpattern.comportamentais.iterator.iterator.ProductIterator;
import br.com.designpattern.comportamentais.iterator.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getAllProducts() {
        // Simula produtos de diferentes fontes
        List<Product> databaseProducts = Arrays.asList(
                new Product("1", "Laptop", 2000.0),
                new Product("2", "Mouse", 50.0)
        );

        List<Product> apiProducts = Arrays.asList(
                new Product("3", "Keyboard", 150.0),
                new Product("4", "Monitor", 300.0)
        );

        ProductCollection dbCollection = new DatabaseProductCollection(databaseProducts);
        ProductCollection apiCollection = new ExternalApiProductCollection(apiProducts);

        List<Product> allProducts = new ArrayList<>();

        ProductIterator dbIterator = dbCollection.createIterator();
        while (dbIterator.hasNext()) {
            allProducts.add(dbIterator.next());
        }

        ProductIterator apiIterator = apiCollection.createIterator();
        while (apiIterator.hasNext()) {
            allProducts.add(apiIterator.next());
        }

        return allProducts;
    }
}
