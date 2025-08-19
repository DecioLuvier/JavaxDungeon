package engine.actors;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Room extends Actor {
    private List<Actor> actors;

    protected Room(Builder builder) {
        super(builder);
        this.actors = new ArrayList<>();
    }

    public void addActor(Actor actor, int x, int y, int z) {
        actor.setPosition(x, y, z);
        actors.add(actor);
    }

    public void addActor(Actor actor) {
        actor.setPosition(0, 0, 0);
        actors.add(actor);
    }

    public void removeActor(Actor actor) { 
        actors.remove(actor); 
    }

    public List<Actor> getActors() { 
        return new ArrayList<>(actors); 
    }

    @Override
    public void setPosition(int x, int y, int z) {
        int deltaX = x - getX();
        int deltaY = y - getY();
        int deltaZ = z - getZ();
        for (Actor actor : getActors()) 
            actor.setPosition(actor.getX() + deltaX, actor.getY() + deltaY, actor.getZ() + deltaZ);
        super.setPosition(x, y, z);
    }

    @Override
    public void draw(Graphics graphics) {
        if (!this.getVisible() || actors.isEmpty()) 
            return;
        this.actors.sort(Comparator.comparingInt(Actor::getZ));
        for (Actor actor : getActors()) 
            if (actor.getVisible()) 
                actor.draw(graphics);
    }

    public void onReleasedKey(KeyEvent event) {
        for (Actor actor : getActors())
            actor.onReleasedKey(event);
    }

    public void onClick(MouseEvent event) {
        for (Actor actor : getActors())
            if (actor.getVisible())
                actor.onClick(event);
    }

    public void onTick() {
        for (Actor actor : getActors())
            actor.onTick();
    }

    
    public static class Builder extends Actor.Builder {
        @Override
        public Room build() {
            return new Room(this);
        }
    }
}