package game.actors.world.button;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;

public class Button extends BoardActor {
	private static SpriteSheet spriteSheet = new SpriteSheet("src/game/actors/world/button/Button.png", 1, 0, 0, 0);

	public Button(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(spriteSheet, 2, 1, 1), row, col);
	}
}
