package game;

import engine.Level;
import engine.actors.VisualActor;
import engine.graphics.Animation;

public class BoardActor extends VisualActor {
    private Action action;
    private int actionTimer;
    private States states;

    public BoardActor(BoardLevel boardLevel, Animation animation, int row, int col, States states) {
        super(animation, col * boardLevel.getGridSize(), row * boardLevel.getGridSize());
        this.states = states;
    }

    public States getStates() {
        return this.states;
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

    public void onHasNoActions(BoardLevel boardLevel) {

    }

    @Override
    public void onTick(Level level) {
        BoardLevel boardLevel = (BoardLevel) level;
        if (!this.hasAction())
            onHasNoActions(boardLevel);
        else {
            if (actionTimer == 0)
                action.onStart(boardLevel, this);
            else if (actionTimer == action.getActionDelay())
                action.onAction(boardLevel, this);
            else if (actionTimer == action.getPostActionDelay())
                action.onPostAction(boardLevel, this);
            else if (actionTimer == action.getEndDelay())
                action.onEnd(boardLevel, this);
            actionTimer++;
        }
        super.onTick(level);
    }
}