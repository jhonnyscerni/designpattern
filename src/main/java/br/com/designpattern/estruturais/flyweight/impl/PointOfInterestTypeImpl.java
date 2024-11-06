package br.com.designpattern.estruturais.flyweight.impl;

import br.com.designpattern.estruturais.flyweight.PointOfInterestType;

public class PointOfInterestTypeImpl implements PointOfInterestType {
    private final String name;
    private final String icon;

    public PointOfInterestTypeImpl(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }
}
