package engine;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.actors.Actor;
import engine.surfaces.Surface;

public class Room {
    private List<Surface> surfaces;

	public Room() {
		this.surfaces = new ArrayList<>();
    }

    public void addSurface(Manager manager, Surface surface) {
        this.surfaces.add(surface);
        surface.onCreate(manager);
    }

    public void removeSurface(Manager manager, Surface surface) {
        this.surfaces.remove(surface);
        surface.onDestroy(manager);
    }

    public void onPressedKey(Manager manager, KeyEvent event) {
        for (Surface surface : surfaces) 
            surface.onPressedKey(manager, event);
    }


    public void onReleasedKey(Manager manager, KeyEvent event) {
        for (Surface surface : surfaces) 
            surface.onReleasedKey(manager, event);
    }


    public void onTick(Manager manager) {
        for (Surface surface : surfaces) 
            surface.onTick(manager);
    }


    public void onCreate(Manager manager) {
        for (Surface surface : surfaces) 
            surface.onCreate(manager);
    }


    public void onDestroy(Manager manager) {
        for (Surface surface : surfaces) 
            surface.onDestroy(manager);
    }

    public List<Actor> getActors() {
        List<Actor> allActors = new ArrayList<>();
        for (Surface surface : surfaces) 
            if(surface.getVisible())
                allActors.addAll(surface.getActors());
        return allActors;
    }

}
