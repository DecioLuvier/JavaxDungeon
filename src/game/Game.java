package game;

import engine.Room;

import java.util.Map;

import engine.Manager;
import game.data.Pokedex;
import game.surfaces.battle.Battle;
import game.surfaces.board.Board;
import game.surfaces.board.Floor;
import game.surfaces.gameboy.Gameboy;
import game.surfaces.menu.Menu;

public class Game extends Room {
    private Gameboy gameboy;
    private Menu menu;
    private Board board;

    public Game() {
        this.gameboy = new Gameboy(0, 0, 0);
        this.menu = new Menu(59, 59, 1);
        this.board = new Board(143, 143, 1);
    }

    @Override
    public void onCreate(Manager manager) {
        addSurface(manager, gameboy);
        //addSurface(manager, menu);
        //addSurface(manager, board);

        Trainer test = new Trainer();


        Pokemon pog = new Pokemon(manager, Pokedex.get("Venusaur"));
        Pokemon pog1 = new Pokemon(manager, Pokedex.get("Charizard"));
        Pokemon pog2 = new Pokemon(manager, Pokedex.get("Blastoise"));

        

        test.addPokemon(pog);
        test.addPokemon(pog1);
        
        Battle batata = new Battle(0, 0, 0, test, pog2 , new Floor(Floor.FloorType.WATER));

        batata.addActor(manager, pog);
        batata.addActor(manager, pog1);
        batata.addActor(manager, pog2);

        addSurface(manager, batata);
    }
}