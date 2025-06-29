package game.actions;

import java.util.List;

import game.Action;
import game.BoardActor;
import game.BoardLevel;
import game.actors.world.floor.Floor;
import game.actors.world.wall.Wall;
import engine.graphics.SpriteSheet;

public class Move extends Action {
    private int row;
    private int col;
    private SpriteSheet idle;
    private SpriteSheet walk;

    public Move(int actionDelay, int postActionDelay, int endDelay, SpriteSheet idle, SpriteSheet walk, int row, int col) {
        super(actionDelay, postActionDelay, endDelay);
        this.idle = idle;
        this.walk = walk;
        this.row = row;
        this.col = col;
    }

    public static boolean canMoveTo(BoardLevel boardLevel, BoardActor boardActor, int row, int col) {
        if (!boardActor.getStates().isMovable())
            return false;

        List<BoardActor> actors = boardLevel.getActors(row, col);
        boolean hasFloor = boardLevel.getActor(actors, Floor.class) != null;
        boolean hasWall = boardLevel.getActor(actors, Wall.class) != null;

        return (boardActor.getStates().isClippable() || !hasWall) && (hasFloor || boardActor.getStates().isFlyable());
    }

    @Override
    public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
        if (!canMoveTo(boardLevel, boardActor, row, col))
            return;

        boardActor.getAnimation().setSpriteSheet(walk);

        if (boardLevel.getCol(boardActor) - col < 0)
            boardActor.getAnimation().setHorizontalOrientation(true);
        else if (boardLevel.getCol(boardActor) - col > 0)
            boardActor.getAnimation().setHorizontalOrientation(false);

        boardLevel.move(boardActor, row, col);
    }

    @Override
    public void onPostAction(BoardLevel boardLevel, BoardActor boardActor) {
        boardActor.getAnimation().setSpriteSheet(idle);
        super.onPostAction(boardLevel, boardActor);
    }

    @Override
    public void onEnd(BoardLevel boardLevel, BoardActor boardActor) {
        super.onEnd(boardLevel, boardActor);
    }
}