package engine.actors;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.Manager;
import engine.Room;
import engine.Surface;
import engine.sprites.Sprite;

public class Actor {
    private Sprite sprite;
    private boolean visible;
    private int x;
    private int y;
    private int z;
    private double xScale;
    private double yScale;

    public Actor(Sprite sprite, double xScale, double yScale) {
        this.sprite = sprite;
        this.visible = true;
        this.xScale = xScale;
        this.yScale = yScale;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Actor(Sprite sprite) {
        this.sprite = sprite;
        this.visible = true;
        this.xScale = 1;
        this.yScale = 1;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }


    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
    public boolean getVisible() { return this.visible; }
    public Sprite getSprite() { return sprite; }
    public double getXScale() { return xScale; }
    public double getYScale() { return yScale; }

    public void setYScale(double yScale) { this.yScale = yScale; }
    public void setXScale(double xScale) { this.xScale = xScale; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setSprite(Sprite sprite) { this.sprite = sprite; }
	public void setPosition(Surface surface, int x, int y, int z){
		this.x = surface.getX() + x;
		this.y = surface.getY() + y;
		this.z = surface.getZ() + z;
	}
	public void setPosition(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}


    public void onReleasedKey(Manager manager, Room room, Surface surface, KeyEvent event) {}
    public void onClick(Manager manager, Room room, Surface surface, MouseEvent event) {}
    public void onTick(Manager manager, Room room, Surface surface) {
        if (this.sprite != null)
            this.sprite.onTick();
    }
}