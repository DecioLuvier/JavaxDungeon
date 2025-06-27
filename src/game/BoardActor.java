package game;

import engine.Level;
import engine.actors.VisualActor;
import engine.graphics.Animation;

public class BoardActor extends VisualActor {
    private Action action;
    private int actionTimer;

    public BoardActor(BoardLevel boardLevel, Animation animation, int row, int col) {
        super(animation);
        boardLevel.move(this, row, col);
    }

    public void endAction() {
        this.action = null;
        this.actionTimer = 0;
    }

    public void setAction(Action action) {
        this.action = action;
        this.actionTimer = 0;
    }

    public boolean hasAction() {
        if (this.action == null)
            return false;
        return true;
    }

    public void onMove(BoardLevel boardLevel) {

    }

    public void onColision(BoardLevel boardLevel, BoardActor boardActor) {

    }

    @Override
    public void onTick(Level level) {
        BoardLevel boardLevel = (BoardLevel) level;
        if (this.hasAction()) {
            if (action.getStartFrames() == actionTimer)
                action.onStart(boardLevel, this);
            if (action.getActionFrames() == actionTimer)
                action.onAction(boardLevel, this);
            if (action.getEndFrames() == actionTimer)
                action.onEnd(boardLevel, this);
            actionTimer++;
        }
        super.onTick(level);
    }
}