package engine;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;

import engine.actors.VisualActor;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;

public class Camera extends JPanel {
    private List<VisualActor> visualActors;

    public Camera() {
        visualActors = new ArrayList<>();
        setDoubleBuffered(true);
    }

    public void draw(List<VisualActor> visualActors) {
        this.visualActors = visualActors;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        visualActors.sort(Comparator.comparingInt(actor -> actor.getAnimation().getRenderOrder()));

        for (VisualActor visualActor : visualActors) {
            Animation animation = visualActor.getAnimation();
            SpriteSheet sprite = animation.getSpriteSheet();
            BufferedImage frame = sprite.getFrame(animation.getFrameIndex());

            int scaledWidth = (int) (frame.getWidth() * animation.getXScale());
            int scaledHeight = (int) (frame.getHeight() * animation.getYScale());

            int drawX = visualActor.getX();
            int drawY = visualActor.getY();
            if (animation.getXScale() < 0)
                drawX += Math.abs(scaledWidth);
            if (animation.getYScale() < 0)
                drawY += Math.abs(scaledHeight);
            drawX -= sprite.getOriginX();
            drawY -= sprite.getOriginY();

            graphics.drawImage(frame, drawX, drawY, scaledWidth, scaledHeight, this);
        }
    }
}