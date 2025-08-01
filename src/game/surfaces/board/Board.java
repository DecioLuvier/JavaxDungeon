package game.surfaces.board;

import engine.Manager;
import engine.actors.VisualActor;
import engine.surfaces.GridSurface;
import game.pokemon.Pokemon;
import game.pokemon.Pokedex;

public class Board extends GridSurface {
    private static int gridSize = 56;

    public Board(int offsetX, int offsetY, int offsetZ) {
        super(offsetX, offsetY, offsetZ, gridSize);
    }

    @Override
    public void onCreate(Manager manager) {
        int N = 8;
        int half = N / 2;

        for (int y = 0; y < N; y++)
            for (int x = 0; x < N; x++)
                if (y < half && x < half)
                    addActor(manager, new Floor(Floor.FloorType.WATER), x, y, 0);
                else if (y < half && x >= half)
                    addActor(manager, new Floor(Floor.FloorType.FLOREST), x, y, 0);
                else if (y >= half && x < half)
                    addActor(manager, new Floor(Floor.FloorType.ROCK), x, y, 0);
                else
                    addActor(manager, new Floor(Floor.FloorType.ICE), x, y, 0);

        //create(manager, new Pokemon(manager, Pokedex.SNORLAX, false), 1, 1, 1);    
        //create(manager, new Pokemon(manager, Pokedex.BULBASAUR, false), 6, 4, 1);  
        //create(manager, new Pokemon(manager, Pokedex.PIKACHU, false), 6, 5, 1);    
        //create(manager, new Pokemon(manager, Pokedex.CHARIZARD, false), 3, 2, 1);  
        //create(manager, new Pokemon(manager, Pokedex.GENGAR, false), 4, 3, 1);     
        //create(manager, new Pokemon(manager, Pokedex.LAPRAS, false), 5, 1, 1);     
        //create(manager, new Pokemon(manager, Pokedex.MEW, false), 2, 4, 1);        
        //create(manager, new Pokemon(manager, Pokedex.DRAGONITE, false), 3, 5, 1);  
        //create(manager, new Pokemon(manager, Pokedex.VENUSAUR, false), 5, 3, 1);   
        //create(manager, new Pokemon(manager, Pokedex.BLASTOISE, false), 2, 2, 1);  
        //create(manager, new Pokemon(manager, Pokedex.ALAKAZAM, false), 4, 1, 1);   
        //create(manager, new Pokemon(manager, Pokedex.GYARADOS, false), 1, 3, 1);   
        //create(manager, new Pokemon(manager, Pokedex.ARCANINE, false), 3, 4, 1);   
        //create(manager, new Pokemon(manager, Pokedex.MACHAMP, false), 5, 5, 1);    
        //create(manager, new Pokemon(manager, Pokedex.JOLTEON, false), 2, 5, 1);    
    }

    public Pokemon getPokemonAt(int row, int col) {
        for (VisualActor actor : getAt(row, col)) 
            if (actor instanceof Pokemon) 
                return (Pokemon) actor;
        return null;
    }

    public Floor getTileAt(int row, int col) {
        for (VisualActor actor : getAt(row, col)) 
            if (actor instanceof Floor) 
                return (Floor) actor;
        return null;
    }
}
