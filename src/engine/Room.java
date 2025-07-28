package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private Manager manager;
    private List<Surface> surfaces;

    public Room(Manager manager) {
        this.manager = manager;
        this.surfaces = new ArrayList<>();
    }

    public List<Surface> getSurfaces() {
        return new ArrayList<>(this.surfaces);
    }

    public Manager getManager() {
        return this.manager;
    }

    public void create(Surface surface) {
        surfaces.add(surface);
        surface.onCreate(this);

    }

    public void destroy(Surface surface) {
        surfaces.remove(surface);
        surface.onDestroy(this);
    }

    public void onPressedKey(KeyEvent event) {
        for (Surface surface : this.getSurfaces())
            surface.onPressedKey(this, event);
    }

    public void onReleasedKey(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_R) {
            onDestroy();
            onCreate();
        } else {
            for (Surface surface : this.getSurfaces())
                surface.onReleasedKey(this, event);
        }
    }

    public void onTick() {
        for (Surface surface : this.getSurfaces())
            surface.onTick(this);
    }

    public void onCreate() {
    }

    public void onDestroy() {
        for (Surface surface : this.getSurfaces())
            destroy(surface);
    }
}
