package game.levels;

import engine.Manager;
import game.BoardLevel;
import game.actors.floor.Floor;
import game.actors.gui.background.Background;
import game.actors.lizard.Lizard;
import game.actors.player.Player;
import game.actors.wall.Wall;

public class Level_01 extends BoardLevel {
    private static int gridSize = 64;

    public Level_01(Manager manager) {
        super(manager, gridSize);
    }

    @Override
    public void onEnter() {
        spawn(new Background(this, 0, 0));

        for (int row = 2; row < 10; row++)
            for (int col = 2; col < 10; col++)
                spawn(new Floor(this, row, col));

        spawn(new Wall(this, 4, 4));

        spawn(new Player(this, 6, 6));

        spawn(new Lizard(this, 8, 6));
    }
}