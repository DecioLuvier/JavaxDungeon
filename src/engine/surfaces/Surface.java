package engine.surfaces;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import engine.Manager;
import engine.actors.Actor;
import engine.actors.VisualActor;

public class Surface {
	private List<Actor> actors;
	private boolean visible;
	private int x;
	private int y;
	private int z;

	public Surface(int x, int y, int z) {
		this.actors = new ArrayList<>();
		this.visible = true;
		this.x = x;
		this.y = y;
		this.z = z;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void setPosition(int x, int y, int z){
		for (Actor actor : this.getActors()) 
			if (actor instanceof VisualActor) 
				setActorPosition((VisualActor) actor, x, y, z);
	}

	public void setPosition(int x, int y) {
		for (Actor actor : this.getActors()) 
			if (actor instanceof VisualActor)
				setActorPosition((VisualActor) actor, x, y); 
	}

	public void addActor(Manager manager, Actor actor) {
		actors.add(actor);
		actor.onCreate(manager, this);
	}

	public void addActor(Manager manager, VisualActor visualActor, int x, int y, int z) {
		setActorPosition(visualActor, x, y, z);
		addActor(manager, visualActor);
	}

	public void removeActor(Manager manager, Actor actor) {
		actors.remove(actor);
		actor.onDestroy(manager, this);
	}

	public void setActorPosition(VisualActor visualActor, int x, int y, int z){
		visualActor.setX(this.x + x);
		visualActor.setY(this.y + y);
		visualActor.setZ(this.z + y);
	}

	public void setActorPosition(VisualActor visualActor, int x, int y){
		visualActor.setX(this.x + x);
		visualActor.setY(this.y + y);
	}

	public void onPressedKey(Manager manager, KeyEvent event) {
		for (Actor actor : this.getActors())
			actor.onPressedKey(manager, this, event);
	}

	public void onReleasedKey(Manager manager, KeyEvent event) {
		for (Actor actor : this.getActors())
			actor.onReleasedKey(manager, this, event);
	}

	public void onTick(Manager manager) {
		for (Actor actor : this.getActors())
			actor.onTick(manager, this);
	}

	public void onCreate(Manager manager) {
	}

	public void onDestroy(Manager manager) {
		for (Actor actor : this.getActors())
			removeActor(manager, actor);
	}
}