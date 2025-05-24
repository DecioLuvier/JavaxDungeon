package game.engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import game.engine.actors.Actor;
import game.engine.actors.BoardActor;

public class Level {
    private List<Actor> actors = new ArrayList<>();

    public void spawn(Actor character) {
        actors.add(character);
    }

    public void despawn(Actor character) {
        actors.remove(character);
    }

    public List<Actor> getActors() {
        return this.actors;
    }

    public <T extends Actor> List<T> getActors(Class<T> cls) {
        List<T> result = new ArrayList<>();
        for (Actor actor : this.actors)
            if (cls.isInstance(actor))
                result.add(cls.cast(actor));
        return result;
    }

    public <T extends BoardActor> List<T> getActors(Class<T> cls, int row, int col) {
        List<T> result = new ArrayList<>();
        for (BoardActor boardActor : getActors(BoardActor.class))
            if (cls.isInstance(boardActor) && boardActor.getRow() == row && boardActor.getCol() == col)
                result.add(cls.cast(boardActor));
        return result;
    }

    public void onEnter() {
    }

    public void onExit() {
        this.actors.clear();
    }

    public void onPressedKey(KeyEvent event) {
        for (Actor actor : this.actors)
            actor.onPressedKey(this, event);
    }

    public void onTick() {
        for (Actor actor : this.actors)
            actor.onTick(this);
    }
}
