package br.com.designpattern.comportamentais.iterador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
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

