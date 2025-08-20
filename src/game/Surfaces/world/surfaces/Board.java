package game.Surfaces.world.surfaces;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Sprite;

import game.Tile;
import game.Surfaces.world.World;
import game.Surfaces.world.commands.SearchCommand;

public class Board extends Surface{
    private final World world;

    private final Actor[][] gridArrows;
    private final Actor[][] gridSprites;
    private final Button[][] gridButtons;

    private static final Sprite boxSprite = new Sprite.Builder().image("assets/gui/world/Box.png").build();

    public Board(World world) {
        super(new Builder()); 
        this.world = world;

        this.gridArrows = new Actor[8][8];
        this.gridButtons = new Button[8][8];
        this.gridSprites = new Actor[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gridArrows[row][col] = new Actor.Builder().sprite(boxSprite).build();
                add(gridArrows[row][col], 84 + (col * 56), 84 + (row * 56), 2);
                gridSprites[row][col] = new Actor.Builder().build();
                add(gridSprites[row][col], 84 + (col * 56), 84 + (row * 56), 1);
                gridButtons[row][col] = new Button.Builder().action(new SearchCommand(world, row, col)).build();
                add(gridButtons[row][col], 84 + (col * 56), 84 + (row * 56), 0);
            }
        }
    }

    public void clear(){
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gridArrows[row][col].setVisible(false);
                gridSprites[row][col].setVisible(false);
                gridSprites[row][col].setSprite(null);
                gridButtons[row][col].setVisible(false);
                gridButtons[row][col].setSprite(null);
            }
        }
    }

    public void update(){
        clear();

        for (Tile tile : world.getTiles()) {
            Button button = gridButtons[tile.getRow()][tile.getCol()];
            button.setVisible(true);
            button.setSprite(tile.getType().getSprite());

            if (tile.getPokemon() != null) {
                if(tile.isShowPokemon() || world.getDebugMode()){
                    Actor actor = gridSprites[tile.getRow()][tile.getCol()];
                    actor.setVisible(true);
                    actor.setSprite(tile.getPokemon().getStats().getSpriteSheet());
                    if(tile.getPokemon().getTrainer() == world.getOpponent())
                        gridArrows[tile.getRow()][tile.getCol()].setVisible(true);
                }
            }
        }
    }
}
