package engine.actors;

import engine.Manager;
import engine.graphic.Animation;
import engine.surfaces.Surface;

public class VisualActor extends Actor {
    private Animation animation;
    private boolean visible;
    private int x;
    private int y;
    private int z;

    public VisualActor(Animation animation, int x, int y, int z, boolean visible) {
        this.animation = animation;
        this.visible = visible;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public VisualActor(Animation animation) {
        this.animation = animation;
        this.visible = true;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Animation getAnimation() {
        return this.animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public int getX() {
        return this.x;
    }

	public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public void onTick(Manager manager, Surface surface) {
        this.animation.onTick();
        super.onTick(manager, surface);
    }
}