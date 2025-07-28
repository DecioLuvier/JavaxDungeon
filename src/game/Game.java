package game;

import engine.Manager;
import engine.Room;
import game.pokemon.Pokedex;
import game.pokemon.Pokemon;
import game.surfaces.battle.Battle;
import game.surfaces.board.Board;
import game.surfaces.board.Floor;
import game.surfaces.gameboy.Gameboy;
import game.surfaces.menu.Menu;
import game.trainer.Trainer;

public class Game extends Room {
    Gameboy gameboy;
    Menu menu;
    Board board;

    public Game(Manager manager) {
        super(manager);
        this.gameboy = new Gameboy(0, 0, 0);
        //this.menu = new Menu(59, 59, 1);
        //this.board = new Board(143, 143, 1);
    }

    @Override
    public void onCreate() {
        create(gameboy);
        //create(menu);
        //create(board);

        Trainer test = new Trainer();


        Pokemon pog = new Pokemon(this, Pokedex.SNORLAX);
        Pokemon pog1 = new Pokemon(this, Pokedex.CHARIZARD);
        Pokemon pog2 = new Pokemon(this, Pokedex.ARCANINE);

        

        test.addPokemon(pog);
        test.addPokemon(pog1);
        
        Battle batata = new Battle(0, 0, 0, test, pog2 , new Floor(Floor.FloorType.WATER));

        batata.create(this, pog);
        batata.create(this, pog1);
        batata.create(this, pog2);

        create(batata);

    }

    public void searchTile(Trainer trainer, int row, int col){
        
    }
}