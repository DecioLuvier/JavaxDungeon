package game;

import engine.Manager;
import game.datas.Pokedex;
import game.rooms.World;

public class Game extends Manager {

    public Game() {
        super(12);

        Trainer player = new Trainer(false);
        player.addPokemon(new Pokemon(Pokedex.get("Onix")));
        player.addPokemon(new Pokemon(Pokedex.get("Charizard")));
        Trainer opponent = new Trainer(true);
        World world = new World(this, player, opponent);
        //Battle battle = new Battle(this, world, player, new Pokemon(Pokedex.get("Farfetch'd")));
        addRoom(world);          
        setCurrentRoom(world);
    }
    
}
