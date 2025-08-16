package game.rooms;

import java.util.ArrayList;
import engine.Manager;
import engine.Room;
import game.Pokemon;
import game.Tile;
import game.Trainer;
import game.datas.Level;
import game.surfaces.Gameboy;
import game.surfaces.world.Board;
import game.surfaces.world.Party;
import game.surfaces.world.Score;

public class World extends Room {
    private Manager manager;
    private ArrayList<Tile> tiles;
    private Trainer player;
    private Trainer opponent;

    private final Gameboy gameboy;
    private final Party party;
    private final Score score;
    private final Board board;

    public World(Manager manager, Trainer player, Trainer opponent) {
        this.manager = manager;
        this.tiles = Level.get("levelBatata");
        this.player = player;
        this.opponent  = opponent;
        
        this.gameboy = new Gameboy();
        this.party = new Party(this);
        this.score = new Score(this);
        this.board = new Board(this);

        addSurface(gameboy, 0, 0, 0);
        addSurface(party, 59, 142, 0);
        addSurface(score, 59, 59, 0);
        addSurface(board, 143, 143, 0);
    }

    public ArrayList<Tile> getTiles() { return tiles; }
    public Trainer getPlayer() { return player; }
    public Trainer getOpponent() { return opponent; }
    public Party getParty() { return party; }

    public void search(Trainer trainer, int row, int col) {
        for(Tile tile : tiles){
            if(tile.getCol() == col && tile.getRow() == row){
                Pokemon pokemon = tile.getPokemon();
                if (pokemon != null){
                    Battle battle = new Battle(manager, this, trainer, pokemon);
                    manager.addRoom(battle);
                    if(!trainer.isNpc())
                        manager.setCurrentRoom(battle);
                }
            }
        }
    }
}