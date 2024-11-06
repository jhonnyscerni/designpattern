package br.com.designpattern.decorator.decorators;

import br.com.designpattern.decorator.service.PriceService;
import org.springframework.stereotype.Service;

@Service
public class SeasonalDiscountDecorator extends PriceDecorator {
    public SeasonalDiscountDecorator(PriceService wrapped) {
        super(wrapped);
    }

    @Override
    public double calculatePrice(double basePrice) {
        double price = super.calculatePrice(basePrice);
        double discount = price * 0.15; // 15% de desconto sazonal
        System.out.println("Aplicando 15% de desconto sazonal.");
        return price - discount;
    }
}