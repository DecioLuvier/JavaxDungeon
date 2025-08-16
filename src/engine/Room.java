package engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import engine.actors.Actor;

public class Room {
    private List<Surface> surfaces;
    private boolean isVisible;
    private boolean isActive;

    public Room() {
        this.surfaces = new ArrayList<>();
    }

    public void addSurface(Surface surface, int x, int y, int z) {
        surfaces.add(surface);
        surface.setPosition(x, y, z);
    }

    public void removeSurface(Surface surface) {
        this.surfaces.remove(surface);
    }

    public List<Surface> getSurfaces() {
       return new ArrayList<>(this.surfaces);
    }

    public void onReleasedKey(Manager manager, KeyEvent event) {
        for (Surface surface : surfaces) 
            for (Actor actor : surface.getActors()) 
                actor.onReleasedKey(manager, this, surface, event);
    }

    public void onClick(Manager manager, MouseEvent event) {
        for (Surface surface : surfaces) 
            if(surface.getVisible())
                for (Actor actor : surface.getActors()) 
                    if(actor.getVisible())
                        actor.onClick(manager, this, surface, event);
    }

    public void onTick(Manager manager) {
        for (Surface surface : surfaces) 
            for (Actor actor : surface.getActors()) 
                actor.onTick(manager, this, surface);
    }
}