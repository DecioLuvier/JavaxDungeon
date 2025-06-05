package game.prefabs.actors.background;

import game.engine.Animation;
import game.engine.Sprite;
import game.engine.actors.VisualActor;

public class Background extends VisualActor {
    private static Sprite sprite = new Sprite("src/game/prefabs/actors/background/Background.png", 1, 0);

    public Background(int x, int y) {
        super(new Animation(sprite, 1, 1), 0, x, y);
    }
}