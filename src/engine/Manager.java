package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import engine.actors.Actor;
import engine.actors.VisualActor;
import game.Game;

public class Manager {
    private Random random;
    private Camera camera;
    private Room room;

    public Manager(JFrame frame) {
        this.random = new Random(12);
        this.camera = new Camera();
        this.room = new Game(this);
        this.room.onCreate();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                room.onReleasedKey(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                room.onPressedKey(e);
            }
        });

        new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room.onTick();

                ArrayList<VisualActor> visualActors = new ArrayList<>();
                for (Surface surface : room.getSurfaces())
                    if(surface.getVisible())
                        for (Actor actor : surface.getActors(VisualActor.class))
                            if (!visualActors.contains((VisualActor) actor))
                                visualActors.add((VisualActor) actor);
                camera.draw(visualActors);
            }
        }).start();

        frame.add(camera);
    }

    public Random getRandom() {
        return this.random;
    }
}
