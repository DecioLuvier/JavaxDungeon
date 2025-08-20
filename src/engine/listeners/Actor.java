package engine.listeners;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.Drawable;
import engine.sprites.Sprite;

public class Actor extends Listener implements Drawable {
    private Sprite sprite;
    private boolean visible;
    private int x;
    private int y;
    private int z;
    private double xScale;
    private double yScale;

    protected Actor(Builder builder) {
        this.sprite = builder.sprite;
        this.visible = builder.visible;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.xScale = builder.xScale;
        this.yScale = builder.yScale;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
    public boolean getVisible() { return this.visible; }
    public double getXScale() { return xScale; }
    public double getYScale() { return yScale; }
    public Sprite getSprite() { return sprite; }

    public void setYScale(double yScale) { this.yScale = yScale; }
    public void setXScale(double xScale) { this.xScale = xScale; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setSprite(Sprite sprite) { this.sprite = sprite; }
    public void setPosition(int x, int y, int z) { this.x = x; this.y = y; this.z = z; }

    public void draw(Graphics graphics) {
        if (!visible || sprite == null || sprite.getImage() == null) 
            return;

        BufferedImage image = sprite.getImage();
        double xScale = this.xScale;
        double yScale = this.yScale;
        int scaledWidth = (int) (image.getWidth() * Math.abs(xScale));
        int scaledHeight = (int) (image.getHeight() * Math.abs(yScale));
        
        int drawX = x - sprite.getOriginX();
        int drawY = y - sprite.getOriginY();
        if (xScale < 0) 
            drawX += scaledWidth;
    
        if (yScale < 0) 
            drawY += scaledHeight;
        graphics.drawImage(image, drawX, drawY, (int) (image.getWidth() * xScale), (int) (image.getHeight() * yScale), null);
    }

    public void onTick() {
        if(this.sprite != null) 
            this.sprite.onTick();
        super.onTick();
     }

    public static class Builder {
        private Sprite sprite = new Sprite.Builder().build();
        private boolean visible = true;
        private int x = 0;
        private int y = 0;
        private int z = 0;
        private double xScale = 1.0;
        private double yScale = 1.0;

        public Builder sprite(Sprite sprite) {
            this.sprite = sprite;
            return this;
        }

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

        public Builder scale(double xScale, double yScale) {
            this.xScale = xScale;
            this.yScale = yScale;
            return this;
        }

        public Actor build() {
            return new Actor(this);
        }
    }
}