package game.actors.world.pikes;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.States;
import game.actions.DestroyActors;

public class Pikes extends BoardActor {
	private static SpriteSheet Idle = new SpriteSheet("src/game/actors/world/pikes/Idle.png", 1, 0, -8, -8);
	private static SpriteSheet Attack = new SpriteSheet("src/game/actors/world/pikes/Attack.png", 5, 5, -8, -8);

	public Pikes(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(Idle, 1, 3, 3), row, col, new States(false, false, false));
	}

	@Override
	public void onColision(BoardLevel boardLevel, BoardActor boardActor) {
		if (!this.hasAction())
			setAction(new DestroyActors(6, 18, 0, Idle, Attack, false, false));
	}
}