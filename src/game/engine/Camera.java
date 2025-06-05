package game.engine;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.engine.actors.VisualActor;

public class Camera extends JPanel {
    private List<VisualActor> visualActors;

    public Camera(JFrame frame) {
        visualActors = new ArrayList<>();
        frame.add(this);
    }

    public void draw(List<VisualActor> visualActors) {
        this.visualActors = visualActors;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        visualActors.sort(Comparator.comparingInt(VisualActor::getRenderOrder));
        for (VisualActor visualActor : visualActors) {
            Animation animation = visualActor.getAnimation();
            BufferedImage frame = animation.getSprite().getFrame(animation.getFrameIndex());

            int scaledWidth = (int) (frame.getWidth() * animation.getXScale());
            int scaledHeight = (int) (frame.getHeight() * animation.getYScale());

            int drawX = visualActor.getX();
            int drawY = visualActor.getY();

            if (animation.getXScale() < 0)
                drawX += Math.abs(scaledWidth);
            if (animation.getYScale() < 0)
                drawY += Math.abs(scaledHeight);

            graphics.drawImage(frame, drawX, drawY, scaledWidth, scaledHeight, this);
        }
    }
}