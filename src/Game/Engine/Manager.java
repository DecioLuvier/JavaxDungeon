package Game.Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

import Game.Assets.Actor;
import Game.Assets.Level;
import Game.Prefabs.Levels.Level_01;

public class Manager {
    Level currentLevel = new Level_01(this);

    public Manager(JFrame frame) {
        Surface surface = new Surface(frame);
        currentLevel.onEnter();

        frame.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                currentLevel.onPressedKey(e);
            }
        });

        Timer timer = new Timer(1000 / 60, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                surface.draw(currentLevel.getActorsByClass(Actor.class));
            }
        });

        timer.start();
    }

    public void changeLevel(Level newLevel) {
        currentLevel.onExit();
        currentLevel = newLevel;
        currentLevel.onEnter();
    }
}
