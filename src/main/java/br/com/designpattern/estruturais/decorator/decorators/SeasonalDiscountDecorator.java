package br.com.designpattern.estruturais.decorator.decorators;

import br.com.designpattern.estruturais.decorator.service.PriceService;

public class SeasonalDiscountDecorator extends PriceDecorator {

    public SeasonalDiscountDecorator(PriceService wrapped) {
        super(wrapped);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        double discount = price * 0.20;
        System.out.println("Aplicando 20% de desconto sazonal.");
        return price - discount;
    }
}