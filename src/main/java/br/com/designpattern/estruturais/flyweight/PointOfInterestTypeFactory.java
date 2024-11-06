package br.com.designpattern.estruturais.flyweight;

import br.com.designpattern.estruturais.flyweight.impl.PointOfInterestTypeImpl;

import java.util.HashMap;
import java.util.Map;

public class PointOfInterestTypeFactory {
    private static final Map<String, PointOfInterestType> types = new HashMap<>();

    public static PointOfInterestType getType(String name, String icon) {
        String key = name + "-" + icon;
        if (!types.containsKey(key)) {
            types.put(key, new PointOfInterestTypeImpl(name, icon));
        }
        return types.get(key);
    }
}
