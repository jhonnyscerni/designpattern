package br.com.designpattern.decorator.service;

import br.com.designpattern.decorator.decorators.LoyaltyDiscountDecorator;
import br.com.designpattern.decorator.decorators.NewUserDiscountDecorator;
import br.com.designpattern.decorator.decorators.SeasonalDiscountDecorator;
import br.com.designpattern.decorator.service.impl.BasicPriceService;
import org.springframework.stereotype.Service;

// Serviço que combina os descontos
@Service
public class DiscountedPriceService {
    private final PriceService basicPriceService;

    public DiscountedPriceService(BasicPriceService basicPriceService) {
        this.basicPriceService = basicPriceService;
    }

    public double calculateDiscountedPrice(double basePrice, boolean isNewUser, boolean isSeasonal, int loyaltyPoints) {
        PriceService decoratedService = basicPriceService;

        // Aplica descontos dinamicamente de acordo com as condições
        if (isNewUser) {
            decoratedService = new NewUserDiscountDecorator(decoratedService);
        }
        if (isSeasonal) {
            decoratedService = new SeasonalDiscountDecorator(decoratedService);
        }
        if (loyaltyPoints > 0) {
            decoratedService = new LoyaltyDiscountDecorator(decoratedService, loyaltyPoints);
        }

        return decoratedService.calculatePrice(basePrice);
    }
}
