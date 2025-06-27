package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.actors.Actor;

public class Level {
    private Manager manager;
    private List<Actor> actors;

    public Level(Manager manager) {
        this.manager = manager;
        this.actors = new ArrayList<>();
    }

    public Manager getManager() {
        return this.manager;
    }

    public List<Actor> getActors() {
        return new ArrayList<>(this.actors);
    }

    public <T extends Actor> List<T> getActors(Class<T> cls) {
        List<T> result = new ArrayList<>();
        for (Actor actor : this.getActors())
            if (cls.isInstance(actor))
                result.add(cls.cast(actor));
        return result;
    }

    public void spawn(Actor actor) {
        actor.onSpawn(this);
        actors.add(actor);
    }

    public void despawn(Actor actor) {
        actor.onDespawn(this);
        actors.remove(actor);
    }

    public void onEnter() {
    }

    public void onExit() {
        this.actors.clear();
    }

    public void onReleasedKey(KeyEvent event) {
        for (Actor actor : this.getActors())
            actor.onReleasedKey(this, event);
    }

    public void onTick() {
        for (Actor actor : this.getActors())
            actor.onTick(this);
    }
}
