package game;

public class States {
	private boolean movable;
	private boolean flyable;
	private boolean clippable;

	public States(boolean movable, boolean flyable, boolean clippable) {
		this.movable = movable;
		this.flyable = flyable;
		this.clippable = clippable;
	}

	public boolean isMovable() {
		return movable;
	}

	public boolean isFlyable() {
		return flyable;
	}

	public boolean isClippable() {
		return clippable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public void setFlyable(boolean flyable) {
		this.flyable = flyable;
	}

	public void setClippable(boolean clippable) {
		this.clippable = clippable;
	}
}
