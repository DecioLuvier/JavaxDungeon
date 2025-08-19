package game.actors.world;

import java.util.ArrayList;
import java.util.Random;

import engine.Manager;
import engine.actors.Actor;
import engine.actors.Button;
import engine.actors.Room;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.Tile;
import game.Trainer;
import game.actors.Intro;
import game.actors.battle.Battle;
import game.datas.Pokedex;
import game.datas.Type;

public class World extends Room {
    // Game state fields
    private ArrayList<Tile> tiles;
    private Trainer player;
    private Trainer opponent;
    private int selectedIndex;
    // UI elements
    private final Actor arrowActor;
    private final Button[] pokemonSlots;
    private final Button[][] gridButtons;
    private final Text scoreText;
    private final Button bagButton;
    private final Button saveButton;
    private final Button exitButton;
    // Static constants
    private static final Sprite SCORE_BACKGROUND = new Sprite.Builder().image("assets/gui/world/Score.png").build();
    private static final Sprite STAR = new Sprite.Builder().image("assets/gui/world/Hint.png").build();
    private static final Sprite SAVE = new Sprite.Builder().image("assets/gui/world/Save.png").build();
    private static final Sprite EXIT = new Sprite.Builder().image("assets/gui/world/Exit.png").build();
    private static final Sprite PARTY_BOX = new Sprite.Builder().image("assets/gui/world/Actions.png").build();
    private static final Sprite ARROW = new Sprite.Builder().image("assets/gui/world/Arrow.png").build();

    public World(Builder builder, Trainer player, Trainer opponent, Intro intro) {
        super(builder);
        this.player = player;
        this.opponent = opponent;
        this.selectedIndex = -1;
        this.tiles = new ArrayList<>();
        this.gridButtons = new Button[8][8];
        // Initialize UI elements
        this.bagButton = new Button.Builder().sprite(STAR).build();
        this.saveButton = new Button.Builder().sprite(SAVE).build();
        this.exitButton = new Button.Builder().sprite(EXIT).action(() -> {
            Room mainRoom = Manager.get().getRoom();
            mainRoom.removeActor(this);
            intro.setVisible(true);
        }).build();
        // Score UI elements
        addActor(new Actor.Builder().sprite(SCORE_BACKGROUND).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(scoreText = new Text.Builder().fontSize(19).build()).build(), 18, 18, 1);
        addActor(bagButton, 312, 21, 1);
        addActor(saveButton, 380, 20, 1);
        addActor(exitButton, 450, 20, 1);
        // Party UI
        this.arrowActor = new Actor.Builder().sprite(ARROW).visible(false).build();
        addActor(arrowActor);
        this.pokemonSlots = new Button[6];
        for (int i = 0; i < pokemonSlots.length; i++) {
            pokemonSlots[i] = new Button.Builder().action(new SwapCommand(this, i)).build();
            addActor(pokemonSlots[i], getX() + 13, getY() + 125 + (i * 60), getZ() + 2);
        }
        addActor(new Actor.Builder().sprite(PARTY_BOX).build(), 0, 83, 1);
        // Board UI
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gridButtons[row][col] = new Button.Builder().action(new SearchCommand(this, row, col)).build();
                addActor(gridButtons[row][col], 84 + (col * 56), 84 + (row * 56), 0);
            }
        }
        // Initialize state
        generateBoard(4,4);
        updateParty();
        setText(player, opponent);
        refresh();
    }

    public void setText(Trainer player, Trainer opponent) {
        scoreText.setText(String.format("Player: %dpts\nOpponent: %dpts", player.getScore(), opponent.getScore()));
    }

    public ArrayList<Tile> getTiles() { return tiles; }
    public Trainer getPlayer() { return player; }
    public Trainer getOpponent() { return opponent; }
    public int getSelectedIndex() { return selectedIndex; }
    public void setSelectedIndex(int selectedIndex) { this.selectedIndex = selectedIndex; }

    public void updateParty() {
        for (int i = 0; i < pokemonSlots.length; i++) {
            Pokemon pokemon = player.getPokemon(i);
            if (pokemon != null && pokemon.getStats() != null && pokemon.getStats().getSpriteSheet() != null) {
                pokemonSlots[i].setSprite(pokemon.getStats().getSpriteSheet());
                pokemonSlots[i].setVisible(true);
            } else {
                pokemonSlots[i].setSprite(null);
                pokemonSlots[i].setVisible(false);
            }

            if (selectedIndex != -1) {
                arrowActor.setVisible(true);
                arrowActor.setPosition(getX() + 13, getY() + 125 + (selectedIndex * 60), getZ() + 3);
            } else {
                arrowActor.setVisible(false);
            }
        }
    }

    public void search(Trainer trainer, int row, int col) {
         for(Tile tile : tiles){
            if(tile.getCol() == col && tile.getRow() == row){
                Pokemon pokemon = tile.getPokemon();
                if (pokemon != null){
                    Battle battle = new Battle(new Room.Builder(), this, trainer, pokemon);
                    Manager.get().getRoom().addActor(battle, 59, 59, 0);
                    if(!trainer.isNpc())
                        this.setVisible(false);
                }
            }
        }
    }

    public void refresh() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gridButtons[row][col].setVisible(false);
                gridButtons[row][col].setSprite(null);
            }
        }

        for (Tile tile : tiles) {
            Button button = gridButtons[tile.getRow()][tile.getCol()];
            button.setVisible(true);
            button.setSprite(tile.getType().getSprite());
        }
    }

    public void generateBoard(int rows, int cols) {
        Random random = Manager.get().getRandom();
        ArrayList<Pokedex> allPokemon = Pokedex.get();
        ArrayList<Type> availableTypes = Type.get();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Type tileType = availableTypes.get(random.nextInt(availableTypes.size()));
                
                ArrayList<Pokedex> validPokemon = new ArrayList<>();
                for (Pokedex pokemon : allPokemon) {
                    Type secondary = pokemon.getSecondaryType();
                    if (pokemon.getMainType().equals(tileType) || (secondary != null && secondary.equals(tileType))) 
                        validPokemon.add(pokemon);
                }
                if (validPokemon.isEmpty()) 
                    continue;

                Pokedex selectedPokedex = validPokemon.get(random.nextInt(validPokemon.size()));
                Pokemon pokemon = new Pokemon(selectedPokedex);
                tiles.add(new Tile(pokemon, tileType, col, row));
            }
        }
        refresh();
    }
}