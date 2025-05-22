package game.engine;

import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.assets.Actor;

public class Surface extends JPanel {
    private List<Actor> actors;

    public Surface(JFrame frame) {
        actors = new ArrayList<>();
        setSize(frame.getWidth(), frame.getHeight());
        frame.add(this);
    }

    public void draw(List<Actor> actors) {
        this.actors = actors;
        this.repaint();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        actors.sort(Comparator.comparingInt(actor -> actor.depth));
        for (Actor actor : actors)
            if (actor.isVisible)
                graphics.drawImage(new ImageIcon(actor.imagePath).getImage(), actor.x, actor.y, this);
    }
}