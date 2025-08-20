package engine.listeners;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import engine.Command;
import engine.sprites.Sprite;

public class Button extends Actor {
    private Command action;

    protected Button(Builder builder) {
        super(builder);
        this.action = builder.action;
    }

    public void setAction(Command action) { 
        this.action = action; 
    }

    @Override
    public void onClick(MouseEvent e) {
        double xScale = super.getXScale();
        double yScale = super.getYScale();
        Sprite sprite = super.getSprite();
        BufferedImage image = sprite != null ? sprite.getImage() : null;
        if (image == null) return;

        int scaledWidth = (int) (image.getWidth() * Math.abs(xScale));
        int scaledHeight = (int) (image.getHeight() * Math.abs(yScale));

        int drawX = getX();
        int drawY = getY();
        if (xScale < 0) drawX -= scaledWidth;
        if (yScale < 0) drawY -= scaledHeight;

        drawX -= sprite.getOriginX();
        drawY -= sprite.getOriginY();

        double mouseX = e.getX();
        double mouseY = e.getY();

        if (mouseX >= drawX && mouseX < drawX + scaledWidth &&
            mouseY >= drawY && mouseY < drawY + scaledHeight) {
            if (action != null) action.execute();
        }
    }

    public static class Builder extends Actor.Builder {
        private Command action;

        public Builder action(Command action) {
            this.action = action;
            return this;
        }

        public Builder sprite(Sprite sprite) {
            super.sprite(sprite);
            return this;
        }

        @Override
        public Button build() {
            return new Button(this);
        }
    }
}
