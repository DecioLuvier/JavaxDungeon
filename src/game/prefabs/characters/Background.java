package game.prefabs.characters;

import game.engine.Sprite;
import game.engine.actors.VisualActor;

public class Background extends VisualActor {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/bg.png";
    private static int SPRITE_DEPTH = 0;

    public Background(int x, int y) {
        super(new Sprite(SPRITE_PATH, SPRITE_DEPTH), x, y);
    }
}
