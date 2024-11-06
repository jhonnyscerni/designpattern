package br.com.designpattern.decorator.service.impl;

import br.com.designpattern.decorator.service.PriceService;
import org.springframework.stereotype.Service;

// Implementação básica de cálculo de preço
@Service
public class BasicPriceService implements PriceService {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice; // Sem descontos
    }
}