package game.levels;

import engine.Manager;
import game.BoardLevel;

import game.actors.characters.player.Player;
import game.actors.world.floor.Floor;
import game.actors.world.pikes.Pikes;
import game.actors.world.wall.Wall;
import game.gui.background.Background;

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

        spawn(new Pikes(this, 8, 6));

    }
}