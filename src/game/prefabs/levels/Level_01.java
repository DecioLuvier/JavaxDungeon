package game.prefabs.levels;

import game.engine.Level;
import game.engine.Manager;
import game.prefabs.actors.background.Background;
import game.prefabs.actors.floor.Floor;
import game.prefabs.actors.player.Player;
import game.prefabs.actors.wall.Wall;

public class Level_01 extends Level {

    public Level_01(Manager manager) {
        super(manager);
    }

    @Override
    public void onEnter() {
        spawn(new Background(0, 0));
        for (int row = 2; row < 10; row++)
            for (int col = 2; col < 10; col++)
                spawn(new Floor(row, col));

        spawn(new Player(5, 8));

        spawn(new Wall(2, 2));
        spawn(new Wall(2, 3));
        spawn(new Wall(2, 4));
        spawn(new Wall(3, 4));
        spawn(new Wall(4, 4));
        spawn(new Wall(4, 3));
        spawn(new Wall(4, 2));
        spawn(new Wall(5, 2));
        spawn(new Wall(6, 6));
        spawn(new Wall(6, 7));
        spawn(new Wall(7, 6));
        spawn(new Wall(8, 6));
        spawn(new Wall(3, 7));
        spawn(new Wall(7, 3));
        spawn(new Wall(8, 8));
    }
}