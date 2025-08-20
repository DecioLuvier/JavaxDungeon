package game.datas;

import game.Pokemon;
import game.Tile;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import engine.Data;

import java.util.ArrayList;

public class Level extends Data {
    private static Level instance; 
    
    private ArrayList<Tile> tiles;

    private Level(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public static Level get() {
        if (instance == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load Level");
            fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files (*.xml)", "xml"));

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    NodeList tileList = Data.loadXML(filePath, "tile");
                    ArrayList<Tile> tiles = new ArrayList<>();
                    for (int i = 0; i < tileList.getLength(); i++) {
                        Element tileElement = (Element) tileList.item(i);
                        int col = Integer.parseInt(tileElement.getAttribute("col"));
                        int row = Integer.parseInt(tileElement.getAttribute("row"));
                        String typeName = tileElement.getAttribute("type");
                        String pokemonName = tileElement.getAttribute("pokemon");
                        tiles.add(new Tile(new Pokemon(Pokedex.get(pokemonName)), Type.get(typeName), col, row));
                    }
                    instance = new Level(tiles);
                    System.out.println("Level loaded from " + filePath);
                } catch (Exception e) {
                    System.err.println("Error loading level: " + e.getMessage());
                    instance = null;
                }
            } else {
                System.out.println("Load operation cancelled by user.");
                instance = null;
            }
        }
        return instance;
    }
}