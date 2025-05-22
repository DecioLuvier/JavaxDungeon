package game.prefabs.levels;

import game.assets.Actor;
import game.assets.Level;
import game.engine.Manager;
import game.prefabs.characters.Ground;
import game.prefabs.characters.Player;
import game.prefabs.characters.Wall;

public class Level_01 extends Level {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/bg.png";
    private static int SPRITE_DEPTH = 0;

    public Level_01(Manager manager) {
        super(manager);
    }

    public void onEnter() {
        spawn(new Actor(SPRITE_PATH, SPRITE_DEPTH, 0, 0));
        spawn(new Wall(0, 0));
        spawn(new Wall(0, 1));
        spawn(new Wall(0, 2));
        spawn(new Wall(0, 3));
        spawn(new Wall(0, 4));

        spawn(new Wall(1, 0));
        spawn(new Ground(1, 1));
        spawn(new Player(1, 1));
        spawn(new Ground(1, 2));
        spawn(new Ground(1, 3));
        spawn(new Wall(1, 4));

        spawn(new Wall(2, 0));
        spawn(new Ground(2, 1));
        spawn(new Wall(2, 2));
        spawn(new Ground(2, 3));
        spawn(new Wall(2, 4));
        spawn(new Wall(2, 5));
        spawn(new Wall(2, 6));

        spawn(new Wall(3, 0));
        spawn(new Ground(3, 1));
        spawn(new Ground(3, 2));
        spawn(new Ground(3, 3));
        spawn(new Ground(3, 4));
        spawn(new Ground(3, 5));

        spawn(new Wall(3, 6));

        spawn(new Wall(4, 0));
        spawn(new Wall(4, 1));
        spawn(new Wall(4, 2));
        spawn(new Wall(4, 3));
        spawn(new Wall(4, 4));
        spawn(new Wall(4, 5));
        spawn(new Wall(4, 6));
    }
}