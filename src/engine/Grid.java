package engine;

import java.util.ArrayList;
import java.util.List;

import engine.actors.VisualActor;

public class Grid extends Surface {
	private int gridSize;

	public Grid(int offsetX, int offsetY, int offsetZ, int gridSize) {
		super(offsetX, offsetY, offsetZ);
		this.gridSize = gridSize;
	}

	@Override
	public void create(Room room, VisualActor visualActor, int row, int col, int z) {
		super.create(room, visualActor, col * gridSize, row * gridSize, z);
	}

	@Override
	public void move(Room room, VisualActor visualActor, int row, int col) {
		super.move(room, visualActor, col * gridSize, row * gridSize);
	}

	public int getRow(VisualActor visualActor) {
		return visualActor.getY() / gridSize;
	}

	public int getCol(VisualActor visualActor) {
		return visualActor.getX() / gridSize;
	}

	public List<VisualActor> getAt(int row, int col) {
		List<VisualActor> result = new ArrayList<>();
		for (VisualActor actor : this.getActors(VisualActor.class))
			if (getRow(actor) == row && getCol(actor) == col)
				result.add(actor);
		return result;
	}

	public List<VisualActor> getInRow(int index) {
		List<VisualActor> result = new ArrayList<>();
		for (VisualActor actor : this.getActors(VisualActor.class))
			if (getRow(actor) == index)
				result.add(actor);
		return result;
	}

	public List<VisualActor> getInCol(int index) {
		List<VisualActor> result = new ArrayList<>();
		for (VisualActor actor : this.getActors(VisualActor.class))
			if (getCol(actor) == index)
				result.add(actor);
		return result;
	}
}
