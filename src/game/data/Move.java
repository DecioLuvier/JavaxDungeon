package game.data;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Move {
    private static Map<String, Move> entries = load();
    private String name;
    private Type type;
    private int power;
    private int energy;
    private int cooldown;
    private boolean isCharged;

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

    private static Map<String, Move> load() {
        Map<String, Move> entries = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("Moves.xml"));
            doc.getDocumentElement().normalize();

            NodeList moveList = doc.getElementsByTagName("move");

            for (int i = 0; i < moveList.getLength(); i++) {
                Element move = (Element) moveList.item(i);
                String name = move.getAttribute("name");
                Move moveObj = new Move(
                    name,
                    Type.get(move.getAttribute("type")),
                    Integer.parseInt(move.getAttribute("power")),
                    Integer.parseInt(move.getAttribute("energy")),
                    Integer.parseInt(move.getAttribute("cooldown")),
                    Boolean.parseBoolean(move.getAttribute("isCharged"))
                );
                entries.put(name, moveObj);
            }

        } catch (Exception e) {
            System.err.println("Error loading moves from XML: " + e.getMessage());
            e.printStackTrace();
        }
        return entries;
    }

    public static Move get(String name) {
        Move move = entries.get(name);
        if (move == null)
            System.err.println("Move with name " + name + " not found.");
        return move;
    }
}