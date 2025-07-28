package game.surfaces.board;

import engine.actors.VisualActor;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;

public class Floor extends VisualActor {
	private FloorType floorType;

	public Floor(FloorType floorType) {
		super(new Animation(floorType.getSpriteSheet(), 1, 1));
		this.floorType = floorType;
	}

	public FloorType getFloorType() {
		return this.floorType;
	}

	public enum FloorType {
		FLOREST("src/game/surfaces/board/Florest.png", 0, 1, 0, 0),
		ICE("src/game/surfaces/board/ice.png", 0, 1, 0, 0),
		ROCK("src/game/surfaces/board/rock.png", 0, 1, 0, 0),
		WATER("src/game/surfaces/board/Water.png", 0, 1, 0, 0);

		private final SpriteSheet spriteSheet;

		FloorType(String spritePath, int frameDelay, int spriteCount, int originX, int originY) {
			this.spriteSheet = new SpriteSheet(spritePath, spriteCount, frameDelay, originX, originY);
		}

		public SpriteSheet getSpriteSheet() {
			return this.spriteSheet;
		}
	}
}
