package engine.surfaces;

import java.util.ArrayList;
import java.util.List;

import engine.Manager;
import engine.actors.Actor;
import engine.actors.VisualActor;

public class GridSurface extends Surface {
	private int gridSize;

	public GridSurface(int offsetX, int offsetY, int offsetZ, int gridSize) {
		super(offsetX, offsetY, offsetZ);
		this.gridSize = gridSize;
	}

	@Override
	public void addActor(Manager manager, VisualActor visualActor, int row, int col, int z){
		setActorPosition(visualActor, col, row, z);
		addActor(manager, visualActor);
	}

	@Override
	public void setActorPosition(VisualActor visualActor, int row, int col, int z){
		super.setActorPosition(visualActor, col * gridSize, row * gridSize, z);
	}
	@Override
	public void setActorPosition(VisualActor visualActor, int row, int col){
		super.setActorPosition(visualActor, col * gridSize, row * gridSize);
	}

	public int getRow(VisualActor visualActor) {
		return visualActor.getY() / gridSize;
	}

	public int getCol(VisualActor visualActor) {
		return visualActor.getX() / gridSize;
	}

	public List<VisualActor> getAt(int row, int col) {
		List<VisualActor> result = new ArrayList<>();
		for (Actor actor : this.getActors()) {
			if (actor instanceof VisualActor) {
				VisualActor visualActor = (VisualActor) actor;
				if (getRow(visualActor) == row && getCol(visualActor) == col) 
					result.add(visualActor);
			}
		}
		return result;
	}

	public List<VisualActor> getInRow(int index) {
		List<VisualActor> result = new ArrayList<>();
		for (Actor actor : this.getActors()) {
			if (actor instanceof VisualActor) {
				VisualActor visualActor = (VisualActor) actor;
				if (getRow(visualActor) == index)
					result.add(visualActor);
			}
		}
		return result;
	}

	public List<VisualActor> getInCol(int index) {
		List<VisualActor> result = new ArrayList<>();
		for (Actor actor : this.getActors()) {
			if (actor instanceof VisualActor) {
				VisualActor visualActor = (VisualActor) actor;
				if (getCol(visualActor) == index)
					result.add(visualActor);
			}
		}
		return result;
	}
}