package engine.actors;

import engine.Room;
import engine.Surface;
import engine.graphics.Animation;

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

    public VisualActor(Animation animation, boolean visible) {
        this.animation = animation;
        this.visible = visible;
        this.x = 0;
        this.y = 0;
        this.z = 0;
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

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public void onTick(Room room, Surface surface) {
        this.animation.onTick();
        super.onTick(room, surface);
    }
}