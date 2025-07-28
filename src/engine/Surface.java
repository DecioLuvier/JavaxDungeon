package engine;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.actors.Actor;
import engine.actors.VisualActor;

public class Surface {
	private List<Actor> actors;
	private int offsetX;
	private int offsetY;
	private int offsetZ;
	private boolean visible;

	public Surface(int offsetX, int offsetY, int offsetZ) {
		this.actors = new ArrayList<>();
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
		this.visible = true;
	}

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

	public void create(Room room, Actor actor) {
		actors.add(actor);
		actor.onCreate(room, this);
	}

	public void create(Room room, VisualActor visualActor, int x, int y, int z) {
		move(room, visualActor, x, y, z);
		create(room, visualActor);
	}

	public void destroy(Room room, Actor actor) {
		actors.remove(actor);
		actor.onDestroy(room, this);
	}

	public void move(Room room, VisualActor visualActor, int x, int y, int z) {
		visualActor.setX(this.offsetX + x);
		visualActor.setY(this.offsetY + y);
		visualActor.setZ(this.offsetZ + z);
	}

	public void move(Room room, VisualActor visualActor, int x, int y) {
		visualActor.setX(this.offsetX + x);
		visualActor.setY(this.offsetY + y);
	}

	public void onPressedKey(Room room, KeyEvent event) {
		for (Actor actor : this.getActors())
			actor.onPressedKey(room, this, event);
	}

	public void onReleasedKey(Room room, KeyEvent event) {
		for (Actor actor : this.getActors())
			actor.onReleasedKey(room, this, event);
	}

	public void onTick(Room room) {
		for (Actor actor : this.getActors())
			actor.onTick(room, this);
	}

	public void onCreate(Room room) {
	}

	public void onDestroy(Room room) {
		for (Actor actor : this.getActors())
			destroy(room, actor);
	}
}