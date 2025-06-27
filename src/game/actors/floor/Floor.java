package game.actors.floor;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;

public class Floor extends BoardActor {
	private static SpriteSheet spriteSheet = new SpriteSheet("src/game/actors/floor/Floor.png", 1, 0, 0, 0);

	public Floor(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(spriteSheet, 2, 1, 1), row, col);
	}
}