package game;

import engine.Manager;
import engine.surfaces.GroupSurface;
//import game.pokemon.Pokedex;
//import game.pokemon.Pokemon;
//import game.surfaces.battle.Battle;
import game.surfaces.board.Board;
//import game.surfaces.board.Floor;
import game.surfaces.gameboy.Gameboy;
import game.surfaces.menu.Menu;
import game.trainer.Trainer;

public class Game extends GroupSurface {
    Gameboy gameboy;
    Menu menu;
    Board board;

    public Game() {
        super(0,0,0);
        this.gameboy = new Gameboy(0, 0, 0);
        this.menu = new Menu(59, 59, 1);
        this.board = new Board(143, 143, 1);
    }

    @Override
    public void onCreate(Manager manager) {
        addSurface(manager, gameboy);
        addSurface(manager, menu);
        addSurface(manager, board);

        //Trainer test = new Trainer();


        //Pokemon pog = new Pokemon(manager, Pokedex.SNORLAX);
        //Pokemon pog1 = new Pokemon(manager, Pokedex.CHARIZARD);
        //Pokemon pog2 = new Pokemon(manager, Pokedex.ARCANINE);
//
        //
//
        //test.addPokemon(pog);
        //test.addPokemon(pog1);
        //
        //Battle batata = new Battle(0, 0, 0, test, pog2 , new Floor(Floor.FloorType.WATER));
//
        //batata.addActor(manager, pog);
        //batata.addActor(manager, pog1);
        //batata.addActor(manager, pog2);

        //addSurface(batata);

    }

    public void searchTile(Trainer trainer, int row, int col){
        
    }
}