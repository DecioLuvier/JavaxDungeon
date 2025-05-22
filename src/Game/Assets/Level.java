package game.assets;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game.engine.Manager;

public class Level {
    Manager manager;
    List<Actor> actors;

    public Level(Manager manager) {
        this.manager = manager;
        this.actors = new ArrayList<>();
    }

    public void spawn(Actor actor) {
        actor.isVisible = true;
        actors.add(actor);
    }

    public void despawn(Actor actor) {
        actors.remove(actor);
    }

    public <T extends Actor> List<T> getActorsByClass(Class<T> cls) {
        return actors.stream().filter(cls::isInstance).map(cls::cast).collect(Collectors.toList());
    }

    public <T extends Character> List<T> getActorsByClass(Class<T> cls, int row, int col) {
        return getActorsByClass(cls).stream().filter(actor -> actor.row == row && actor.col == col)
                .collect(Collectors.toList());
    }

    public void onEnter() {
    }

    public void onExit() {
        actors.clear();
    }

    public void onPressedKey(KeyEvent event) {
        for (Character character : getActorsByClass(Character.class))
            character.onPressedKey(this, event);
    }
}
