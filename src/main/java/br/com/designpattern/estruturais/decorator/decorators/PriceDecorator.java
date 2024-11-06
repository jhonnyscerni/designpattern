package br.com.designpattern.decorator.decorators;

import br.com.designpattern.decorator.service.PriceService;

// Decorator abstrato para descontos
public abstract class PriceDecorator implements PriceService {
    protected PriceService wrapped;

    public PriceDecorator(PriceService wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return wrapped.calculatePrice(basePrice);
    }
}