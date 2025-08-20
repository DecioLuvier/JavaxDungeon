package engine;

import java.awt.Graphics;

public interface Drawable {
    int getX();
    int getY();
    int getZ();
    boolean getVisible();
    void draw(Graphics graphics);
    void setPosition(int x, int y, int z);
}