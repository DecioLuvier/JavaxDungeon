package engine.actors;

import engine.Level;
import engine.graphics.Animation;

public class VisualActor extends Actor {
    private Animation animation;
    private int x;
    private int y;

    public VisualActor(Animation animation, int x, int y) {
        this.animation = animation;
        this.x = x;
        this.y = y;
    }

    public Animation getAnimation() {
        return this.animation;
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
        super.onTick(level);
    }
}