package game.assets;

import java.awt.event.KeyEvent;

public class Character extends Actor {
    public int row;
    public int col;

    public Character(String imagePath, int depth, int row, int col) {
        super(imagePath, depth, 0, 0);
        move(row, col);
    }

    public void move(int row, int col) {
        this.x = col * 64;
        this.y = row * 64;
        this.row = row;
        this.col = col;
    }

    public void onPressedKey(Level level, KeyEvent e) {
    }
}