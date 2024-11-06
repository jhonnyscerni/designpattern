package br.com.designpattern.decorator.controller;

import br.com.designpattern.decorator.service.DiscountedPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Exemplo de uso no Controller
@RestController
@RequestMapping("/price")
public class PriceController {
    private final DiscountedPriceService discountedPriceService;

    public PriceController(DiscountedPriceService discountedPriceService) {
        this.discountedPriceService = discountedPriceService;
    }

    ///price/calculate?basePrice=100.0&isNewUser=true&isSeasonal=true&loyaltyPoints=10
    @GetMapping("/calculate")
    public ResponseEntity<String> calculatePrice(@RequestParam double basePrice,
                                                 @RequestParam boolean isNewUser,
                                                 @RequestParam boolean isSeasonal,
                                                 @RequestParam int loyaltyPoints) {
        double finalPrice = discountedPriceService.calculateDiscountedPrice(basePrice, isNewUser, isSeasonal, loyaltyPoints);
        return ResponseEntity.ok("Preço final com descontos: " + finalPrice);
    }
}