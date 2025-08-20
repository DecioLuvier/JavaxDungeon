package game.Surfaces.intro.commands;

import java.util.ArrayList;

import engine.Command;
import engine.Manager;
import game.Pokemon;
import game.Tile;
import game.Surfaces.intro.Intro;
import game.Surfaces.world.World;
import game.datas.Type;

public class GenerateWorld extends Command {
    private final Intro intro;

    public GenerateWorld(Intro intro) {
        this.intro = intro;
    }

    @Override
    public void execute() {
        World world = new World(generate(8, 8));
        Manager manager = Manager.get();
        manager.add(world, 59, 59, 1);
        manager.remove(intro);
    }

    public static ArrayList<Tile> generate(int rows, int cols) {
        Type waterType = Type.get("Water");
        Type grassType = Type.get("Grass");
        Type groundType = Type.get("Ground");
        Type electricType = Type.get("Electric");

        ArrayList<Tile> tiles = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Type tileType;
                if (row < rows / 2 && col < cols / 2)
                    tileType = waterType;
                else if (row < rows / 2 && col >= cols / 2)
                    tileType = grassType;
                else if (row >= rows / 2 && col < cols / 2)
                    tileType = groundType;
                else
                    tileType = electricType;

                // 30% chance for the tile to have no Pokémon
                Pokemon pokemon = null;
                if (Manager.get().getRandom().nextDouble() > 0.3) {
                    pokemon = Pokemon.generateBasePokemon(tileType);
                }

                tiles.add(new Tile(pokemon, tileType, col, row));
            }
        }
        return tiles;
    }
}