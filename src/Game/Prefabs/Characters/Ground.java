package game.prefabs.characters;

import game.engine.Sprite;
import game.engine.actors.BoardActor;

public class Ground extends BoardActor {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/ground.png";
    private static int SPRITE_DEPTH = 1;

    public Ground(int row, int col) {
        super(new Sprite(SPRITE_PATH, SPRITE_DEPTH), row, col);
    }
}
