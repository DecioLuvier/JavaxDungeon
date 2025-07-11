package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.Level;
import engine.Manager;
import game.actors.world.box.Box;

public class BoardLevel extends Level {
    private int gridSize;

    public BoardLevel(Manager manager) {
        super(manager);
        this.gridSize = 64;
    }

    public <T extends BoardActor> T getActor(List<BoardActor> actors, Class<T> cls) {
        for (BoardActor actor : actors)
            if (cls.isInstance(actor))
                return cls.cast(actor);
        return null;
    }

    public List<BoardActor> getActors(int row, int col) {
        List<BoardActor> result = new ArrayList<>();
        for (BoardActor boardActor : this.getActors(BoardActor.class))
            if (this.getRow(boardActor) == row && this.getCol(boardActor) == col)
                result.add(boardActor);
        return result;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public int getRow(BoardActor boardActor) {
        return boardActor.getY() / gridSize;
    }

    public int getCol(BoardActor boardActor) {
        return boardActor.getX() / gridSize;
    }

    public void move(BoardActor boardActor, int row, int col) {
        boardActor.setPosition(col * gridSize, row * gridSize);
    }

    public void onAllBoxesOnTarget() {
    }

    @Override
    public void onReleasedKey(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_R) {
            this.onExit();
            this.onEnter();
        }
        super.onReleasedKey(event);
    }

    @Override
    public void onTick() {
        for (Box box : this.getActors(Box.class)) {
            if (!box.isOnTarget()) {
                super.onTick();
                return;
            }
        }

        onAllBoxesOnTarget();
        super.onTick();
    }
}
