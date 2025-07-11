package game;

import engine.actors.VisualActor;
import engine.graphics.Animation;

public class BoardActor extends VisualActor {
    public BoardActor(BoardLevel boardLevel, Animation animation, int row, int col) {
        super(animation, col * boardLevel.getGridSize(), row * boardLevel.getGridSize());
    }
}