package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import engine.actors.Actor;
import engine.sprites.Sprite;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Manager extends JPanel {
    private final Random random;
    private final List<Room> rooms = new ArrayList<>();
    private Room currentRoom;

    public Manager(int seed) {
        this.random = new Random(seed);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                currentRoom.onReleasedKey(Manager.this, e);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                currentRoom.onClick(Manager.this, e);
            }
        });

        new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Room room : rooms) 
                    room.onTick(Manager.this);
                repaint();
            }
        }).start();

        setDoubleBuffered(true);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    public Random getRandom() {
        return this.random;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        ArrayList<Actor> visualActors = new ArrayList<>();
        for (Surface surface : currentRoom.getSurfaces()) 
            if (surface.getVisible()) 
                for (Actor actor : surface.getActors()) 
                    if (actor.getVisible()) 
                        visualActors.add(actor);

        visualActors.sort(Comparator.comparingInt(Actor::getZ));

        for (Actor visualActor : visualActors) {
            if (!visualActor.getVisible()) 
                continue;
            double xScale = visualActor.getXScale();
            double yScale = visualActor.getYScale();
            Sprite sprite = visualActor.getSprite();
            BufferedImage image = sprite.getImage();

            int scaledWidth = (int) (image.getWidth() * xScale);
            int scaledHeight = (int) (image.getHeight() * yScale);

            int drawX = visualActor.getX();
            int drawY = visualActor.getY();
            if (xScale < 0) drawX += Math.abs(scaledWidth);
            if (yScale < 0) drawY += Math.abs(scaledHeight);
            drawX -= sprite.getOriginX();
            drawY -= sprite.getOriginY();
            
            graphics.drawImage(image, drawX, drawY, scaledWidth, scaledHeight, this);
        }
    }


}
