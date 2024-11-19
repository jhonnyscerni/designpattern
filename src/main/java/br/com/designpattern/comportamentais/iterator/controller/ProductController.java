package br.com.designpattern.comportamentais.iterator.controller;

import br.com.designpattern.comportamentais.iterator.model.Product;
import br.com.designpattern.comportamentais.iterator.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/iterator/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
