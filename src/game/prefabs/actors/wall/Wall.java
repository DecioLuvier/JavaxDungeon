package game.prefabs.actors.wall;

import game.engine.Animation;
import game.engine.Sprite;
import game.engine.actors.BoardActor;

public class Wall extends BoardActor {
	private static Sprite idle = new Sprite("src/game/prefabs/actors/wall/Wall.png", 1, 0);

	public Wall(int row, int col) {
		super(new Animation(idle, 1, 1), 2, row, col);
	}
}