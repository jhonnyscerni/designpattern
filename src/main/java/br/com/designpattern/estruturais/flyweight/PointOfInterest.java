package br.com.designpattern.estruturais.flyweight;

public class PointOfInterest {
    private final PointOfInterestType type; // Flyweight
    private final double latitude;
    private final double longitude;
    private final String specificData;

    public PointOfInterest(PointOfInterestType type, double latitude, double longitude, String specificData) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.specificData = specificData;
    }

    public void display() {
        System.out.println("POI: " + type.getName() + " at (" + latitude + ", " + longitude + ") - " + specificData);
    }
}
