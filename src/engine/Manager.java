package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import engine.actors.Actor;
import engine.actors.VisualActor;
import engine.graphic.Animation;
import engine.graphic.SpriteSheet;
import engine.surfaces.Surface;
import game.Game;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

public class Manager extends JPanel {
    private Random random;
    private Surface surface;

    public Manager(int seed) {
        this.random = new Random(seed);
        this.surface = new Game();
        this.surface.onCreate(this);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                surface.onReleasedKey(Manager.this, e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                surface.onPressedKey(Manager.this, e);
            }
        });

        new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                surface.onTick(Manager.this);
                repaint();
            }
        }).start();

        setDoubleBuffered(true);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ArrayList<VisualActor> visualActors = new ArrayList<>();
        for (Actor actor : surface.getActors()){
			if (actor instanceof VisualActor) {
				VisualActor visualActor = (VisualActor) actor;
                if(visualActor.getVisible() && !visualActors.contains(visualActor))
                    visualActors.add(visualActor);
            }
        }

        visualActors.sort(Comparator.comparingInt(actor -> actor.getZ()));

        for (VisualActor visualActor : visualActors) {
            if(visualActor.getVisible()){
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

    public Random getRandom() {
        return this.random;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }
}
