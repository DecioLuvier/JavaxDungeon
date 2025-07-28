package game.surfaces.gameboy;

import engine.Room;
import engine.Surface;
import engine.actors.VisualActor;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;

public class Gameboy extends Surface {
	private static SpriteSheet longButton = new SpriteSheet("src/game/surfaces/gameboy/Long.png", 1, 0, 0, 0);
	private static SpriteSheet roundButton = new SpriteSheet("src/game/surfaces/gameboy/Round.png", 1, 0, 0, 0);
	private static SpriteSheet visor = new SpriteSheet("src/game/surfaces/gameboy/Visor.png", 1, 0, 0, 0);
	private static SpriteSheet dpad = new SpriteSheet("src/game/surfaces/gameboy/Dpad.png", 1, 0, 0, 0);
	
	public Gameboy(int offsetX, int offsetY, int offsetZ) {
		super(offsetX, offsetY, offsetZ);
	}

	@Override
	public void onCreate(Room room) {
        create(room, new VisualActor(new Animation(visor, 1, 1)), 0, 0, 0);
        create(room, new VisualActor(new Animation(roundButton, 3, 3)), 475, 735, 1);
        create(room, new VisualActor(new Animation(roundButton, 3, 3)), 525, 685, 1);
        create(room, new VisualActor(new Animation(longButton, 3, 3)), 270, 770, 1);
		create(room, new VisualActor(new Animation(longButton, 3, 3)), 340, 770, 1);
		create(room, new VisualActor(new Animation(dpad, 3, 3)), 50, 660, 1);
	}
}
