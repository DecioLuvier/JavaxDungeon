package game.prefabs.characters;

import game.engine.Sprite;
import game.engine.actors.BoardActor;

public class Wall extends BoardActor {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/wall.png";
    private static int SPRITE_DEPTH = 2;

    public Wall(int row, int col) {
        super(new Sprite(SPRITE_PATH, SPRITE_DEPTH), row, col);
    }
}
