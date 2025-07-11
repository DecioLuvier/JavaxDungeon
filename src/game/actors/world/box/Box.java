package game.actors.world.box;

import engine.Level;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.actors.world.button.Button;

public class Box extends BoardActor {
	private static SpriteSheet box = new SpriteSheet("src/game/actors/world/box/Box.png", 1, 0, 0, 0);
	private static SpriteSheet boxOnTarget = new SpriteSheet("src/game/actors/world/box/BoxOnTarget.png", 1, 0, 0, 0);

	public Box(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(box, 3, 1, 1), row, col);
	}

	@Override
	public void onTick(Level level) {
		BoardLevel boardLevel = (BoardLevel) level;

		if (boardLevel.getActor(boardLevel.getActors(boardLevel.getRow(this), boardLevel.getCol(this)), Button.class) == null)
			this.getAnimation().setSpriteSheet(box);
		else
			this.getAnimation().setSpriteSheet(boxOnTarget);

		super.onTick(level);
	}

	public boolean isOnTarget() {
		if (this.getAnimation().getSpriteSheet() == boxOnTarget)
			return true;
		else
			return false;
	}
}
