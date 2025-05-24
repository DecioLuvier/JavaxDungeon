package game.engine.actors;

import game.engine.Sprite;

public class BoardActor extends VisualActor {
    private int row;
    private int col;

    public BoardActor(Sprite sprite, int row, int col) {
        super(sprite, col * 64, row * 64);
        this.row = row;
        this.col = col;
    }

    public void setPosition(int row, int col) {
        super.setPosition(col * 64, row * 64);
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}