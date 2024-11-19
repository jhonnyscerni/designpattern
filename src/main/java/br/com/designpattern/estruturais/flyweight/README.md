
# Padrão Flyweight em Java

O padrão **Flyweight** é uma solução para otimizar o uso de memória em sistemas que lidam com muitos objetos semelhantes. Ele reduz a redundância de dados ao compartilhar partes comuns entre objetos, sendo particularmente útil em cenários com alto volume de instâncias com dados semelhantes.

---

## Contexto

Em um sistema de mapas que exibe Pontos de Interesse (**POIs**), cada POI contém informações como:
- Tipo (ex.: Restaurante, Posto de Gasolina).
- Ícone associado.
- Coordenadas (latitude, longitude).
- Informações específicas.

**Problema:** Sem o Flyweight, informações repetidas, como tipo e ícone, são duplicadas em cada instância, levando a um consumo elevado de memória.

---

## Antes do Padrão Flyweight

Cada POI armazena todos os dados individualmente, incluindo informações que poderiam ser compartilhadas.

### Implementação

```java
public class PointOfInterest {
    private final String type;       // Tipo do POI
    private final String icon;       // Ícone do POI
    private final double latitude;   // Coordenada latitude
    private final double longitude;  // Coordenada longitude
    private final String specificData; // Informações específicas

    public PointOfInterest(String type, String icon, double latitude, double longitude, String specificData) {
        this.type = type;
        this.icon = icon;
        this.latitude = latitude;
        this.longitude = longitude;
        this.specificData = specificData;
    }

    public void display() {
        System.out.println("POI: " + type + " at (" + latitude + ", " + longitude + ") - " + specificData);
    }
}
```

### Problemas

1. **Redundância de Dados:** Cada POI armazena seu próprio `type` e `icon`, mesmo quando múltiplos POIs compartilham o mesmo tipo.
2. **Escalabilidade Limitada:** O consumo de memória aumenta exponencialmente à medida que o número de POIs cresce.

---

## Depois do Padrão Flyweight

Com o Flyweight, extraímos os dados compartilhados (`type` e `icon`) para uma classe separada e criamos instâncias únicas desses dados.

---

### 1. Interface `PointOfInterestType`

Define o comportamento dos dados compartilhados.

```java
public interface PointOfInterestType {
    String getName();
    String getIcon();
}
```

---

### 2. Implementação: `PointOfInterestTypeImpl`

Armazena o tipo e o ícone como um Flyweight.

```java
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
```

---

### 3. Fábrica: `PointOfInterestTypeFactory`

Garante que cada tipo único de POI seja criado apenas uma vez.

```java
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
```

---

### 4. Classe Refatorada: `PointOfInterest`

Agora, cada POI armazena apenas dados específicos e compartilha o tipo.

```java
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
```

---

### 5. Uso do Flyweight

Criamos os POIs usando a `PointOfInterestTypeFactory`.

```java
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
```

---

## Vantagens do Padrão Flyweight

1. **Redução de Memória:**
   - Os tipos de POI (`type`, `icon`) são compartilhados entre instâncias.
   - Apenas os dados específicos (ex.: coordenadas) são armazenados individualmente.

2. **Eficiência:**
   - Adequado para sistemas com grande volume de objetos semelhantes.

3. **Manutenção Simplificada:**
   - Modificar um tipo de POI afeta todas as instâncias compartilhadas, reduzindo a necessidade de alterações múltiplas.

---

## Resultado

Para um grande número de POIs, o padrão **Flyweight** otimiza o consumo de memória e melhora a escalabilidade do sistema, tornando-o ideal para aplicações como mapas, jogos e outros sistemas com estruturas repetitivas.
