package game.engine;

import java.awt.Graphics;

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
        setSize(frame.getWidth(), frame.getHeight());
        frame.add(this);
    }

    public void draw(List<VisualActor> visualActors) {
        this.visualActors = visualActors;
        this.repaint();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        visualActors.sort(Comparator.comparingInt(character -> character.getSprite().getRenderOrder()));
        for (VisualActor visualActor : visualActors) {
            Sprite sprite = visualActor.getSprite();
            if (sprite.getVisible())
                graphics.drawImage(sprite.getImage(), visualActor.getX(), visualActor.getY(), this);
        }
    }
}