package game.data;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Move {
    private final String name;
    private final Type type;
    private final int power;
    private final int energy;
    private final int cooldown;
    private final boolean isCharged;

    private Move(String name, Type type, int power, int energy, int cooldown, boolean isCharged) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.energy = energy;
        this.cooldown = cooldown;
        this.isCharged = isCharged;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isCharged() {
        return isCharged;
    }

    public int getCooldown() {
        return cooldown;
    }

    public static Move get(String Name) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("Moves.xml"));
            doc.getDocumentElement().normalize();

            NodeList moveList = doc.getElementsByTagName("move");

            for (int i = 0; i < moveList.getLength(); i++) {
                Element move = (Element) moveList.item(i);
                
                if (move.getAttribute("name").equals(Name)) {
                    String name = move.getAttribute("name");
                    Type type = Type.get(move.getAttribute("type"));
                    int power = Integer.parseInt(move.getAttribute("power"));
                    int energy = Integer.parseInt(move.getAttribute("energy"));
                    int cooldown = Integer.parseInt(move.getAttribute("cooldown"));
                    boolean isCharged = Boolean.parseBoolean(move.getAttribute("isCharged"));
                    return new Move(name, type, power, energy, cooldown, isCharged);
                }
            }

            System.err.println("Move with name " + Name + " not found.");
            return null;

        } catch (Exception e) {
            System.err.println("Error loading moves from XML: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}