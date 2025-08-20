package engine.listeners;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.Drawable;

public class Surface extends Listener implements Drawable {
    private List<Drawable> drawables;
    private boolean visible;
    private int x;
    private int y;
    private int z;

    protected Surface(Builder builder) {
        this.drawables = new ArrayList<>();
        this.visible = builder.visible;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
    }

    public int getX() { 
        return x; 
    }

    public int getY() { 
        return y; 
    }

    public int getZ() { 
        return z; 
    }

    public void add(Drawable drawable, int x, int y, int z) {
        drawable.setPosition(this.x + x, this.y + y,this.z +  z);
        drawables.add(drawable);
    }

    public void add(Drawable drawable) {
        drawable.setPosition(x, y, z);
        drawables.add(drawable);
    }

    public void remove(Drawable drawable) {
        drawables.remove(drawable);
    }

    public boolean getVisible() { 
        return this.visible; 
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Drawable> getDrawables() { 
        return new ArrayList<>(drawables);  
    }

    private List<Listener> getListeners() { 
        List<Listener> drawableActors = new ArrayList<>();
        for (Drawable drawable : getDrawables())
            if(drawable instanceof Listener)
                drawableActors.add((Listener) drawable);
        return new ArrayList<>(drawableActors);  
    }

    public void onReleasedKey(KeyEvent event) {
        for (Listener actor : getListeners())
            actor.onReleasedKey(event);
    }

    public void onClick(MouseEvent event) {
        if(this.visible)
            for (Listener actor : getListeners())
                actor.onClick(event);
    }

    public void onTick() {
        for (Listener actor : getListeners())
            actor.onTick();
    }

    public void draw(Graphics graphics) {
        drawables.sort(Comparator.comparingInt(Drawable::getZ));
        for (Drawable drawable : getDrawables()) 
            if (drawable.getVisible()) 
                drawable.draw(graphics);
    }

    @Override
    public void setPosition(int x, int y, int z) {
        int deltaX = x - getX();
        int deltaY = y - getY();
        int deltaZ = z - getZ();
        for (Drawable drawable : getDrawables()) 
            drawable.setPosition(drawable.getX() + deltaX, drawable.getY() + deltaY, drawable.getZ() + deltaZ);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static class Builder {
        private boolean visible = true;
        private int x = 0;
        private int y = 0;
        private int z = 0;

        public Builder visible(boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public Surface build() {
            return new Surface(this);
        }
    }
}
