package game.datas;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Move {
    private static NodeList moveList = Data.loadXML("assets/Moves.xml", "move");
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

    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPower() { return power; }
    public int getEnergy() { return energy; }
    public boolean isCharged() { return isCharged; }
    public int getCooldown() { return cooldown; }

    public static Move get(String name) {
        if (name == null || name.isEmpty()) 
            return null;
        for (int i = 0; i < moveList.getLength(); i++) {
            Element moveElement = (Element) moveList.item(i);
            String moveName = moveElement.getAttribute("name");
            if (moveName.equals(name)) {
                return new Move(
                    moveName,
                    Type.get(moveElement.getAttribute("type")),
                    Integer.parseInt(moveElement.getAttribute("power")),
                    Integer.parseInt(moveElement.getAttribute("energy")),
                    Integer.parseInt(moveElement.getAttribute("cooldown")),
                    Boolean.parseBoolean(moveElement.getAttribute("isCharged"))
                );
            }
        }
        System.err.println("Move with name " + name + " not found.");
        return null;
    }
}