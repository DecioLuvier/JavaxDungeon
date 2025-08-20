package game.Surfaces.world;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import engine.Manager;
import engine.listeners.Surface;
import game.Pokemon;
import game.Tile;
import game.Trainer;
import game.Surfaces.battle.Battle;
import game.Surfaces.intro.Intro;
import game.Surfaces.world.surfaces.Board;
import game.Surfaces.world.surfaces.Party;
import game.Surfaces.world.surfaces.Score;

public class World extends Surface {
    private final ArrayList<Tile> tiles;
    private final Trainer player;
    private final Trainer opponent;
    private boolean isHintSelected;
    private int hintRemain;
    private final Party party;
    private final Score score;
    private final Board board;
    private transient Thread cpuThread;
    private volatile boolean running = true;
    private boolean debugMode = false;
    private int xpTickCounter;

    public World(ArrayList<Tile> tiles) {
        super(new Builder());
        this.isHintSelected = false;
        this.hintRemain = 3;
        this.score = new Score(this);
        this.board = new Board(this);
        this.party = new Party(this);
        this.tiles = tiles;
        this.xpTickCounter = 0;
        this.player = new Trainer(false);
        this.opponent = new Trainer(true);

        this.player.addPokemon(Pokemon.generateBasePokemon(null));
        this.opponent.addPokemon(Pokemon.generateBasePokemon(null));

        add(party, 448, -82, 0);
        add(score, 0, 448, 0);
        add(board, -83, -83, 0);

        party.update();
        score.update();
        board.update();

        startCpuOpponent();
    }

    public Trainer getPlayer() { return player; }
    public Trainer getOpponent() { return opponent; }
    public ArrayList<Tile> getTiles() { return tiles; }
    public Board getBoard() { return board; }
    public Party getParty() { return party; }
    public Score getScore() { return score; }
    public boolean isHintSelected() { return isHintSelected; }
    public void setHintSelected(boolean isHintSelected) { this.isHintSelected = isHintSelected; }
    public int getHintRemain() { return hintRemain; }
    public void setHintRemain(int hintRemain) { this.hintRemain = hintRemain; }
    public boolean getDebugMode() { return this.debugMode; }

    @Override
    public void onReleasedKey(KeyEvent event) {
        super.onReleasedKey(event);
        if (event.getKeyCode() == KeyEvent.VK_D) {
            debugMode = !debugMode;
            board.update();
        }
    }

    @Override
    public void onTick() {
        xpTickCounter++;
        if (xpTickCounter >= 200) {
            for(Tile tile : tiles){
                Pokemon pokemon = tile.getPokemon();
                if (pokemon != null && !pokemon.isOnBattle()) 
                    pokemon.addExperience(5);
            }
            xpTickCounter = 0;
        }

        if (areAllPokemonCaptured()) {
            Intro intro = new Intro();
            Manager manager = Manager.get();
            manager.add(intro, 60, 60 ,1);
            manager.remove(this);
        }
        super.onTick();
    }

    public void search(Trainer trainer, int row, int col) {
        synchronized (tiles) {
            for (Tile tile : tiles) {
                if (tile.getCol() == col && tile.getRow() == row) {
                    Pokemon pokemon = tile.getPokemon();
                    if (pokemon != null && !pokemon.isOnBattle()) {
                        Battle battle = new Battle(this, trainer, pokemon);
                        pokemon.setOnBattle(true);
                        Manager.get().add(battle, 59, 59, 0);
                        trainer.setOnBattle(true);
                        if (trainer.isNpc())
                            battle.setVisible(false);
                        else
                            this.setVisible(false);
                        return;
                    }
                }
            }
        }
    }

    public void hint(int row, int col) {
        synchronized (tiles) {
            for (Tile tile : tiles)
                if (tile.getCol() == col || tile.getRow() == row)
                    tile.setShowPokemon(true);
            isHintSelected = false;
            hintRemain -= 1;
            board.update();
            score.update();
        }
    }

    private boolean areAllPokemonCaptured() {
        synchronized (tiles) {
            for (Tile tile : tiles) 
                if (tile.getPokemon() != null) 
                    return false; 
            return true; 
        }
    }

    private void startCpuOpponent() {
        cpuThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(100);
                    if (!opponent.isOnBattle()) {
                        synchronized (tiles) {
                            Tile selectedTile = tiles.get(Manager.get().getRandom().nextInt(tiles.size()));
                            search(opponent, selectedTile.getRow(), selectedTile.getCol());
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    running = false;
                }
            }
        });
        cpuThread.start();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        running = false;
        if (cpuThread != null) {
            try {
                cpuThread.interrupt();
                cpuThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        running = true;
        startCpuOpponent();
    }
}