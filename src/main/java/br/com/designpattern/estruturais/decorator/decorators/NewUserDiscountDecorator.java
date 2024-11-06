package br.com.designpattern.decorator.decorators;

import br.com.designpattern.decorator.service.PriceService;
import org.springframework.stereotype.Service;

// Decorator de desconto para novos usuários
@Service
public class NewUserDiscountDecorator extends PriceDecorator {
    public NewUserDiscountDecorator(PriceService wrapped) {
        super(wrapped);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        double discount = price * 0.1; // 10% de desconto para novos usuários
        System.out.println("Aplicando 10% de desconto para novos usuários.");
        return price - discount;
    }
}