package game;

import engine.Level;
import engine.actors.VisualActor;
import engine.graphics.Animation;

public class BoardActor extends VisualActor {
    private Action action;
    private int actionTimer;
    private States states;

    public BoardActor(BoardLevel boardLevel, Animation animation, int row, int col, States states) {
        super(animation);
        this.states = states;
        boardLevel.move(this, row, col);
    }

    public States getStates() {
        return this.states;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
        this.actionTimer = 0;
    }

    public void onMove(BoardLevel boardLevel) {

    }

    public void onColision(BoardLevel boardLevel, BoardActor boardActor) {

    }

    public void onHasNoActions(BoardLevel boardLevel) {

    }

    @Override
    public void onTick(Level level) {
        BoardLevel boardLevel = (BoardLevel) level;
        if (this.getAction() == null)
            onHasNoActions(boardLevel);
        else {
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