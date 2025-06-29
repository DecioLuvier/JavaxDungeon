package game.actors.lizard;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.States;
import game.actions.RandomMovement;

public class Lizard extends BoardActor {
	private static SpriteSheet idle = new SpriteSheet("src/game/actors/lizard/Idle.png", 4, 4, 10, 5);
	private static SpriteSheet walk = new SpriteSheet("src/game/actors/lizard/Walk.png", 4, 3, 15, 15);

	public Lizard(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(idle, 3, 5, 5), row, col, new States(true, false, false));
	}

	@Override
	public void onHasNoActions(BoardLevel boardLevel) {
		this.setAction(new RandomMovement(0, 20, 0, idle, walk));
	}
}
