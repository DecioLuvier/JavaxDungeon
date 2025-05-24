package game.prefabs.levels;

import game.engine.Level;
import game.prefabs.characters.Background;
import game.prefabs.characters.Ground;
import game.prefabs.characters.Player;
import game.prefabs.characters.Wall;

public class Level_01 extends Level {
    public void onEnter() {
        spawn(new Background(0, 0));
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