package game.gui.background;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import engine.Level;
import engine.actors.VisualActor;

public class Background extends VisualActor {
    private static SpriteSheet spriteSheet = new SpriteSheet("src/game/gui/background/Background.png", 1, 0, 0, 0);

    public Background(Level level, int x, int y) {
        super(level, new Animation(spriteSheet, 0, 1, 1), x, y);
    }
}