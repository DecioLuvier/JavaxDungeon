package game.actors.world.fireball;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.States;
import game.actions.Move;

public class Fireball extends BoardActor {
	private static SpriteSheet idle = new SpriteSheet("src/game/actors/world/fireball/Idle.png", 1, 0, 0, 0);
	private boolean faceRight;

	public Fireball(BoardLevel boardLevel, int row, int col, boolean faceRight) {
		super(boardLevel, new Animation(idle, 3, 3, 3), row, col, new States(true, true, false));
		this.faceRight = faceRight;
		this.getAnimation().setHorizontalOrientation(faceRight);
	}

	@Override
	public void onHasNoActions(BoardLevel boardLevel) {
		int currentCol = boardLevel.getCol(this);
		if (this.faceRight)
			currentCol++;
		else
			currentCol--;
		setAction(new Move(0, 0, 0, idle, idle, boardLevel.getRow(this), currentCol));
		super.onHasNoActions(boardLevel);
	}
}
