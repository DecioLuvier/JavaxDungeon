package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import engine.actors.Room;

import java.awt.Graphics;

public class Manager extends JPanel {
    private static Manager instance = null; 
    private final Random random;
    private final Room room;

    private Manager() {
        this.random = new Random();
        this.room = new Room.Builder().build();

        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(room.getVisible())
                    room.onReleasedKey(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
                if(room.getVisible())
                    room.onClick(e);
            }
        });

        new Timer(1000 / 60, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                room.onTick();
                repaint();
            }
        }).start();

        setDoubleBuffered(true);
    }

    public static Manager get() {
        if (instance == null) 
            instance = new Manager();
        return instance;
    }

    public Random getRandom() {
        return this.random;
    }

    public Room getRoom() {
        return room;
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        room.draw(graphics);
    }
}