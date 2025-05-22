package game.assets;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
        List<T> result = new ArrayList<>();
        for (Actor actor : actors)
            if (cls.isInstance(actor))
                result.add(cls.cast(actor));
        return result;
    }

    public <T extends Character> List<T> getActorsByClass(Class<T> cls, int row, int col) {
        List<T> filtered = new ArrayList<>();
        for (T actor : getActorsByClass(cls))
            if (actor.row == row && actor.col == col)
                filtered.add(actor);
        return filtered;
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
