package game.levels;

import engine.Manager;
import game.BoardLevel;
import game.actors.player.Player;
import game.actors.world.background.Background;
import game.actors.world.box.Box;
import game.actors.world.button.Button;
import game.actors.world.floor.Floor;
import game.actors.world.wall.Wall;

public class Level_09 extends BoardLevel {
    public Level_09(Manager manager) {
        super(manager);
    }

    @Override
    public void onEnter() {
        spawn(new Background(this, 0, 0));
        spawn(new Wall(this, 0, 2));
        spawn(new Wall(this, 0, 3));
        spawn(new Wall(this, 0, 4));
        spawn(new Wall(this, 0, 5));
        spawn(new Wall(this, 0, 6));
        spawn(new Wall(this, 1, 0));
        spawn(new Wall(this, 1, 1));
        spawn(new Wall(this, 1, 2));
        spawn(new Wall(this, 1, 6));
        spawn(new Wall(this, 2, 0));
        spawn(new Wall(this, 2, 6));
        spawn(new Wall(this, 3, 0));
        spawn(new Wall(this, 3, 2));
        spawn(new Wall(this, 3, 3));
        spawn(new Wall(this, 3, 5));
        spawn(new Wall(this, 3, 6));
        spawn(new Wall(this, 4, 0));
        spawn(new Player(this, 4, 3));
        spawn(new Wall(this, 4, 6));
        spawn(new Wall(this, 5, 0));
        spawn(new Box(this, 5, 2));
        spawn(new Box(this, 5, 3));
        spawn(new Button(this, 5, 3));
        spawn(new Box(this, 5, 4));
        spawn(new Button(this, 5, 4));
        spawn(new Button(this, 5, 5));
        spawn(new Wall(this, 5, 6));
        spawn(new Wall(this, 6, 0));
        spawn(new Wall(this, 6, 3));
        spawn(new Wall(this, 6, 6));
        spawn(new Wall(this, 7, 0));
        spawn(new Wall(this, 7, 1));
        spawn(new Wall(this, 7, 2));
        spawn(new Wall(this, 7, 3));
        spawn(new Wall(this, 7, 4));
        spawn(new Wall(this, 7, 5));
        spawn(new Wall(this, 7, 6));
        spawn(new Floor(this, 1, 0));
        spawn(new Floor(this, 2, 0));
        spawn(new Floor(this, 3, 0));
        spawn(new Floor(this, 4, 0));
        spawn(new Floor(this, 5, 0));
        spawn(new Floor(this, 6, 0));
        spawn(new Floor(this, 7, 0));
        spawn(new Floor(this, 1, 1));
        spawn(new Floor(this, 2, 1));
        spawn(new Floor(this, 3, 1));
        spawn(new Floor(this, 4, 1));
        spawn(new Floor(this, 5, 1));
        spawn(new Floor(this, 6, 1));
        spawn(new Floor(this, 7, 1));
        spawn(new Floor(this, 0, 2));
        spawn(new Floor(this, 1, 2));
        spawn(new Floor(this, 2, 2));
        spawn(new Floor(this, 3, 2));
        spawn(new Floor(this, 4, 2));
        spawn(new Floor(this, 5, 2));
        spawn(new Floor(this, 6, 2));
        spawn(new Floor(this, 7, 2));
        spawn(new Floor(this, 0, 3));
        spawn(new Floor(this, 1, 3));
        spawn(new Floor(this, 2, 3));
        spawn(new Floor(this, 3, 3));
        spawn(new Floor(this, 4, 3));
        spawn(new Floor(this, 5, 3));
        spawn(new Floor(this, 6, 3));
        spawn(new Floor(this, 7, 3));
        spawn(new Floor(this, 0, 4));
        spawn(new Floor(this, 1, 4));
        spawn(new Floor(this, 2, 4));
        spawn(new Floor(this, 3, 4));
        spawn(new Floor(this, 4, 4));
        spawn(new Floor(this, 5, 4));
        spawn(new Floor(this, 6, 4));
        spawn(new Floor(this, 7, 4));
        spawn(new Floor(this, 0, 5));
        spawn(new Floor(this, 1, 5));
        spawn(new Floor(this, 2, 5));
        spawn(new Floor(this, 3, 5));
        spawn(new Floor(this, 4, 5));
        spawn(new Floor(this, 5, 5));
        spawn(new Floor(this, 6, 5));
        spawn(new Floor(this, 7, 5));
        spawn(new Floor(this, 0, 6));
        spawn(new Floor(this, 1, 6));
        spawn(new Floor(this, 2, 6));
        spawn(new Floor(this, 3, 6));
        spawn(new Floor(this, 4, 6));
        spawn(new Floor(this, 5, 6));
        spawn(new Floor(this, 6, 6));
        spawn(new Floor(this, 7, 6));
    }

    @Override
    public void onAllBoxesOnTarget() {
        getManager().setCurrentLevel(new Level_10(getManager()));
    }
}