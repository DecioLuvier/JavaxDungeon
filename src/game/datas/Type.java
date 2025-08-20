package game.datas;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import engine.Data;
import engine.sprites.Sprite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Type extends Data{
    private static final ArrayList<Type> entries = new ArrayList<>();
    private String name;
    private Map<String, Double> resistances;

    private Type(String name, Map<String, Double> resistances) {
        this.name = name;
        this.resistances = resistances;
    }

    public String getName() { return name; }
    public Map<String, Double> getResistances() { return resistances; }
    public Sprite getSprite() { return new Sprite.Builder().image("assets/types/tiles/" + name + ".png").build(); }

    public static ArrayList<Type> get() {
        if (entries.size() == 0) {
            NodeList typeList = Data.loadXML("assets/Types.xml", "type");
            for (int i = 0; i < typeList.getLength(); i++) {
                Element type = (Element) typeList.item(i);
                String name = type.getAttribute("name");
                Map<String, Double> resistances = new HashMap<>();
                NodeList resistanceList = type.getElementsByTagName("resistance");
                for (int j = 0; j < resistanceList.getLength(); j++) {
                    Element resistance = (Element) resistanceList.item(j);
                    String resistType = resistance.getAttribute("type");
                    double value = Double.parseDouble(resistance.getAttribute("value"));
                    resistances.put(resistType, value);
                }
                entries.add(new Type(name, resistances));
            }
        }
        return entries;
    }

    public static Type get(String name) {
        if (name == null || name.isEmpty()) 
            return null;
        for (Type t : get()) 
            if (t.name.equalsIgnoreCase(name)) 
                return t;
        System.err.println("Type with name " + name + " not found.");
        return null;
    }

    public static ArrayList<Type> getAllTypes() {
        return get();
    }
}