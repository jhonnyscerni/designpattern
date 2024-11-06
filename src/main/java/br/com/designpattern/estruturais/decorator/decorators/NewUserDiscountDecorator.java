package br.com.designpattern.estruturais.decorator.decorators;

import br.com.designpattern.estruturais.decorator.service.PriceService;

public class NewUserDiscountDecorator extends PriceDecorator {

    public NewUserDiscountDecorator(PriceService wrapped) {
        super(wrapped);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        double discount = price * 0.15;
        System.out.println("Aplicando 15% de desconto para novos usuários.");
        return price - discount;
    }
}