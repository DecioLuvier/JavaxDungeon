package game.prefabs.characters;

import java.awt.event.KeyEvent;

import game.assets.Character;
import game.assets.Level;

public class Player extends Character {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/player.png";
    private static int SPRITE_DEPTH = 2;

    public Player(int row, int col) {
        super(SPRITE_PATH, SPRITE_DEPTH, row, col);
    }

    public void onPressedKey(Level level, KeyEvent e) {
        int newRow = this.row;
        int newCol = this.col;

        switch (e.getKeyCode()) {
        case KeyEvent.VK_W -> newRow--;
        case KeyEvent.VK_S -> newRow++;
        case KeyEvent.VK_A -> newCol--;
        case KeyEvent.VK_D -> newCol++;
        }

        if (canMoveTo(level, newRow, newCol))
            move(newRow, newCol);
    }

    private boolean canMoveTo(Level level, int row, int col) {
        boolean hasGround = level.getActorsByClass(Ground.class, row, col).size() > 0;
        boolean hasWall = level.getActorsByClass(Wall.class, row, col).size() > 0;

        return hasGround && !hasWall;
    }
}
