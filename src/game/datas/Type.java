package game.datas;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import engine.sprites.Sprite;

import java.util.HashMap;
import java.util.Map;

public class Type {
    private static NodeList typeList = Data.loadXML("assets/Types.xml", "type");
    private String name;
    private Map<String, Double> resistances;

    private Type(String name, Map<String, Double> resistances) {
        this.name = name;
        this.resistances = resistances;
    }

    public String getName() { return name; }
    public Map<String, Double> getResistances() { return resistances; }
    public Sprite getSprite() { return new Sprite("assets/types/tiles/" + name + ".png"); }

    public static Type get(String name) {
        if(name.isEmpty() || name == null)
            return null;
        for (int i = 0; i < typeList.getLength(); i++) {
            Element type = (Element) typeList.item(i);
            if (type.getAttribute("name").equals(name)) {
                Map<String, Double> resistances = new HashMap<>();
                NodeList resistanceList = type.getElementsByTagName("resistance");
                for (int j = 0; j < resistanceList.getLength(); j++) {
                    Element resistance = (Element) resistanceList.item(j);
                    String resistType = resistance.getAttribute("type");
                    double value = Double.parseDouble(resistance.getAttribute("value"));
                    resistances.put(resistType, value);
                }
                return new Type(name, resistances);
            }
        }
        return null;
    }
}