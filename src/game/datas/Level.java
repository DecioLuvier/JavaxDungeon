package game.datas;

import game.Pokemon;
import game.Tile;
import org.w3c.dom.*;

import java.util.ArrayList;

public class Level {
    private static NodeList levelList = Data.loadXML("assets/Levels.xml", "level");

    public static ArrayList<Tile> get(String name) {
        if (name == null || name.isEmpty()) 
            return null;
        for (int i = 0; i < levelList.getLength(); i++) {
            Element levelElement = (Element) levelList.item(i);
            if (levelElement.getAttribute("name").equals(name)) {
                ArrayList<Tile> tiles = new ArrayList<>();
                NodeList tileList = levelElement.getElementsByTagName("tile");
                for (int j = 0; j < tileList.getLength(); j++) {
                    Element tileElement = (Element) tileList.item(j);
                    int col = Integer.parseInt(tileElement.getAttribute("col"));
                    int row = Integer.parseInt(tileElement.getAttribute("row"));
                    String typeName = tileElement.getAttribute("type");
                    String pokemonName = tileElement.getAttribute("pokemon");
                    tiles.add(new Tile(new Pokemon(Pokedex.get(pokemonName)), Type.get(typeName), col, row));
                }
                return tiles;
            }
        }
        System.err.println("Level with name " + name + " not found.");
        return null;
    }
}