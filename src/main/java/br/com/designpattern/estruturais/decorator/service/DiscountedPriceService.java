package br.com.designpattern.estruturais.decorator.service;

import br.com.designpattern.estruturais.decorator.decorators.LoyaltyDiscountDecorator;
import br.com.designpattern.estruturais.decorator.decorators.NewUserDiscountDecorator;
import br.com.designpattern.estruturais.decorator.decorators.SeasonalDiscountDecorator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DiscountedPriceService {
    private final PriceService basicPriceService;

    public DiscountedPriceService(@Qualifier("basicPriceService") PriceService basicPriceService) {
        this.basicPriceService = basicPriceService;
    }

    public double calculateDiscountedPrice(double basePrice, boolean isNewUser, boolean isSeasonal, int loyaltyPoints) {
        PriceService decoratedService = basicPriceService;

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