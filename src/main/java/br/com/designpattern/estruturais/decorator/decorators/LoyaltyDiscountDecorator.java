package br.com.designpattern.estruturais.decorator.decorators;

import br.com.designpattern.estruturais.decorator.service.PriceService;

public class LoyaltyDiscountDecorator extends PriceDecorator {
    private final int loyaltyPoints;

    public LoyaltyDiscountDecorator(PriceService wrapped, int loyaltyPoints) {
        super(wrapped);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        double discount = price * 0.10;
        System.out.println("Aplicando 10% de desconto de fidelidade.");
        return price - discount;
    }
}