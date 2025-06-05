package game.engine.actors;

import game.engine.Animation;

public class BoardActor extends VisualActor {
    private int row;
    private int col;

    public BoardActor(Animation animation, int renderOrder, int row, int col) {
        super(animation, renderOrder, col * 64, row * 64);
        this.row = row;
        this.col = col;
    }

    @Override
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