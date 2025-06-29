package game;

import java.util.ArrayList;
import java.util.List;

import engine.Level;

import engine.Manager;

public class BoardLevel extends Level {
    private int gridSize;

    public BoardLevel(Manager manager, int gridSize) {
        super(manager);
        this.gridSize = gridSize;
    }

    public <T extends BoardActor> boolean hasActorType(List<BoardActor> actors, Class<T> cls) {
        for (BoardActor actor : actors)
            if (cls.isInstance(actor))
                return true;
        return false;
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
        boardActor.onMove(this);
        for (BoardActor otherBoardActor : getActors(row, col))
            otherBoardActor.onColision(this, boardActor);
    }
}
