package game.prefabs.actors.floor;

import game.engine.Animation;
import game.engine.Sprite;
import game.engine.actors.BoardActor;

public class Floor extends BoardActor {
	private static Sprite idle = new Sprite("src/game/prefabs/actors/floor/Floor.png", 1, 0);

	public Floor(int row, int col) {
		super(new Animation(idle, 1, 1), 2, row, col);
	}
}