package game.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import game.engine.actors.VisualActor;
import game.prefabs.levels.Level_01;

public class Manager {
    Level currentLevel = new Level_01();

    public Manager(JFrame frame) {
        Camera camera = new Camera(frame);
        currentLevel.onEnter();

        frame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                currentLevel.onPressedKey(e);
            }
        });

        Timer timer = new Timer(1000 / 60, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentLevel.onTick();
                camera.draw(currentLevel.getActors(VisualActor.class));
            }
        });

        timer.start();
    }
}
