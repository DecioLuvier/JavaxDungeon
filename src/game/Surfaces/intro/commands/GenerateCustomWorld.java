package game.Surfaces.intro.commands;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.NodeList;

import engine.Command;
import engine.Data;
import engine.Manager;
import game.Pokemon;
import game.Tile;
import game.Surfaces.intro.Intro;
import game.Surfaces.world.World;
import game.datas.Pokedex;
import game.datas.Type;

import org.w3c.dom.Element;


public class GenerateCustomWorld extends Command{
    private final Intro intro;

    public GenerateCustomWorld(Intro intro) {
        this.intro = intro;
    }

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Level");
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML Files (*.xml)", "xml"));

        int userSelection = fileChooser.showOpenDialog(null);
        ArrayList<Tile> tiles = new ArrayList<>();

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                NodeList tileList = Data.loadXML(filePath, "tile");
                for (int i = 0; i < tileList.getLength(); i++) {
                    Element tileElement = (Element) tileList.item(i);
                    int col = Integer.parseInt(tileElement.getAttribute("col"));
                    int row = Integer.parseInt(tileElement.getAttribute("row"));
                    String typeName = tileElement.getAttribute("type");
                    String pokemonName = tileElement.getAttribute("pokemon");
                    tiles.add(new Tile(new Pokemon(Pokedex.get(pokemonName)), Type.get(typeName), col, row));
                }
                World world = new World(tiles);
                Manager manager = Manager.get();
                manager.add(world, 59, 59 ,1);
                manager.remove(intro);
            } catch (Exception e) {
                System.err.println("Error loading level: " + e.getMessage());
            }
        } else {
            System.out.println("Load operation cancelled by user.");
        }
    }
}
