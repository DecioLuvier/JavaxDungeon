package engine.actors;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import engine.Manager;
import engine.Room;
import engine.Surface;
import engine.sprites.Sprite;

public class Button extends Actor {
    private Runnable action;

    public Button(Sprite animation, Runnable action) {
        super(animation, 1, 1);
        this.action = action;
    }

    @Override
    public void onClick(Manager manager, Room room, Surface surface, MouseEvent e) {
        double xScale = super.getXScale();
        double yScale = super.getYScale();
        Sprite sprite = super.getSprite();
        BufferedImage image = sprite != null ? sprite.getImage() : null;
        if (image == null) return;

        int scaledWidth = (int) (image.getWidth() * Math.abs(xScale));
        int scaledHeight = (int) (image.getHeight() * Math.abs(yScale));

        int drawX = getX();
        int drawY = getY();
        if (xScale < 0)
            drawX -= scaledWidth;
        if (yScale < 0)
            drawY -= scaledHeight;

        drawX -= sprite.getOriginX();
        drawY -= sprite.getOriginY();

        double mouseX = e.getX();
        double mouseY = e.getY();

        if (mouseX >= drawX && mouseX < drawX + scaledWidth && mouseY >= drawY && mouseY < drawY + scaledHeight)
            if (action != null) 
                action.run();
    }

    public void setAction(Runnable action) {
        this.action = action;
    }
}