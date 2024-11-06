package br.com.designpattern.estruturais.flyweight;

public class WithFlyweight {
    public static void main(String[] args) {
        PointOfInterestType restaurantType = PointOfInterestTypeFactory.getType("Restaurant", "restaurant_icon.png");
        PointOfInterestType gasStationType = PointOfInterestTypeFactory.getType("Gas Station", "gas_icon.png");

        PointOfInterest poi1 = new PointOfInterest(restaurantType, 40.7128, -74.0060, "Joe's Pizza");
        PointOfInterest poi2 = new PointOfInterest(restaurantType, 40.7138, -74.0160, "Central Sushi");
        PointOfInterest poi3 = new PointOfInterest(gasStationType, 40.7135, -74.0059, "Shell Gas Station");

        poi1.display();
        poi2.display();
        poi3.display();
    }
}
