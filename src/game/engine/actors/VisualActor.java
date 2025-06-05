package game.engine.actors;

import game.engine.Level;
import game.engine.Animation;

public class VisualActor extends Actor {
    private Animation animation;
    private int renderOrder;
    private int x;
    private int y;

    public VisualActor(Animation animation, int renderOrder, int x, int y) {
        this.animation = animation;
        this.renderOrder = renderOrder;
        this.x = x;
        this.y = y;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public int getRenderOrder() {
        return this.renderOrder;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void onTick(Level level) {
        this.animation.onTick();
    }
}