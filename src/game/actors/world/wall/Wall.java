package game.actors.world.wall;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.States;

public class Wall extends BoardActor {
	private static SpriteSheet spriteSheet = new SpriteSheet("src/game/actors/world/wall/Wall.png", 1, 0, 0, 0);

	public Wall(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(spriteSheet, 1, 1, 1), row, col, new States(false, false, false));
	}
}