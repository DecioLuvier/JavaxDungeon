package game.actors.world.wall;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;

public class Wall extends BoardActor {
	private static SpriteSheet spriteSheet = new SpriteSheet("src/game/actors/world/wall/Wall.png", 1, 0, 0, 0);

	public Wall(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(spriteSheet, 3, 1, 1), row, col);
	}
}