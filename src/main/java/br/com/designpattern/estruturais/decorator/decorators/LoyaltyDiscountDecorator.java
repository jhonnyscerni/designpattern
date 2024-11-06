package br.com.designpattern.decorator.decorators;

import br.com.designpattern.decorator.service.PriceService;
import org.springframework.stereotype.Service;

// Decorator de desconto por pontos de fidelidade
@Service
public class LoyaltyDiscountDecorator extends PriceDecorator {
    private final int loyaltyPoints;

    public LoyaltyDiscountDecorator(PriceService wrapped, int loyaltyPoints) {
        super(wrapped);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        double discount = loyaltyPoints * 0.05; // Cada ponto de fidelidade vale 5% de desconto
        System.out.println("Aplicando desconto de fidelidade.");
        return price - Math.min(discount, price); // Limita o desconto para não ser negativo
    }
}
