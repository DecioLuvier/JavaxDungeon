package engine.surfaces;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.Manager;
import engine.actors.Actor;

public class GroupSurface extends Surface {
    private List<Surface> surfaces;

	public GroupSurface(int offsetX, int offsetY, int offsetZ) {
		super(offsetX, offsetY, offsetZ);
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

	@Override
	public void setPosition(int x, int y, int z) {
		super.setPosition(x, y, z);
		for (Surface surface : surfaces) 
			surface.setPosition(x + surface.getX(), y + surface.getY(), z + surface.getZ());
	}

	@Override
	public void setPosition(int x, int y) {
		super.setPosition(x, y);
		for (Surface surface : surfaces) 
			surface.setPosition(x + surface.getX(), y + surface.getY());
	}

	@Override
    public void onPressedKey(Manager manager, KeyEvent event) {
        super.onPressedKey(manager, event);
        for (Surface surface : surfaces) 
            surface.onPressedKey(manager, event);
    }

    @Override
    public void onReleasedKey(Manager manager, KeyEvent event) {
        super.onReleasedKey(manager, event);
        for (Surface surface : surfaces) 
            surface.onReleasedKey(manager, event);
    }

    @Override
    public void onTick(Manager manager) {
        super.onTick(manager);
        for (Surface surface : surfaces) 
            surface.onTick(manager);
    }

    @Override
    public void onCreate(Manager manager) {
        super.onCreate(manager);
        for (Surface surface : surfaces) 
            surface.onCreate(manager);
    }

    @Override
    public void onDestroy(Manager manager) {
        super.onDestroy(manager);
        for (Surface surface : surfaces) 
            surface.onDestroy(manager);
    }

    @Override
    public List<Actor> getActors() {
        List<Actor> allActors = new ArrayList<>(super.getActors());
        for (Surface surface : surfaces) 
            if(surface.getVisible())
                allActors.addAll(surface.getActors());
        return allActors;
    }

}
