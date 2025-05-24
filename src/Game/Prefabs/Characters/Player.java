package game.prefabs.characters;

import java.awt.event.KeyEvent;

import game.engine.Level;
import game.engine.Sprite;
import game.engine.actors.BoardActor;

public class Player extends BoardActor {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/player.png";
    private static int SPRITE_DEPTH = 2;

    public Player(int row, int col) {
        super(new Sprite(SPRITE_PATH, SPRITE_DEPTH), row, col);
    }

    public void onPressedKey(Level level, KeyEvent e) {
        int newRow = this.getRow();
        int newCol = this.getCol();

        switch (e.getKeyCode()) {
        case KeyEvent.VK_W -> newRow--;
        case KeyEvent.VK_S -> newRow++;
        case KeyEvent.VK_A -> newCol--;
        case KeyEvent.VK_D -> newCol++;
        }

        if (canMoveTo(level, newRow, newCol))
            this.setPosition(newRow, newCol);
    }

    private boolean canMoveTo(Level level, int row, int col) {
        boolean hasGround = level.getActors(Ground.class, row, col).size() > 0;
        boolean hasWall = level.getActors(Wall.class, row, col).size() > 0;

        return hasGround && !hasWall;
    }
}
