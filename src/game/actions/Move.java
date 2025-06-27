package game.actions;

import java.util.List;

import game.Action;
import game.BoardActor;
import game.BoardLevel;
import game.actors.floor.Floor;
import engine.graphics.SpriteSheet;

public class Move extends Action {
    private int row;
    private int col;
    private SpriteSheet idle;
    private SpriteSheet walk;

    public Move(int startFrames, int actionFrames, int endFrames, int row, int col, SpriteSheet idle,
            SpriteSheet walk) {
        super(startFrames, actionFrames, endFrames);
        this.row = row;
        this.col = col;
        this.idle = idle;
        this.walk = walk;
    }

    @Override
    public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
        boardActor.getAnimation().setSpriteSheet(walk);

        if (boardLevel.getCol(boardActor) - col > 0)
            boardActor.getAnimation().setHorizontalOrientation(true);
        else if (boardLevel.getCol(boardActor) - col < 0)
            boardActor.getAnimation().setHorizontalOrientation(false);

        List<BoardActor> actors = boardLevel.getActors(row, col);
        boolean hasFloor = boardLevel.hasActorType(actors, Floor.class);

        if (hasFloor)
            boardLevel.move(boardActor, row, col);
    }

    @Override
    public void onEnd(BoardLevel boardLevel, BoardActor boardActor) {
        boardActor.getAnimation().setSpriteSheet(idle);
        super.onEnd(boardLevel, boardActor);
    }
}