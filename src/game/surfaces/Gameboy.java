package game.surfaces;

import engine.Surface;
import engine.actors.Actor;
import engine.sprites.Sprite;

public class Gameboy extends Surface {
	private static Sprite longButton = new Sprite("assets/gui/gameboy/Long.png");
	private static Sprite roundButton = new Sprite("assets/gui/gameboy/Round.png");
	private static Sprite visor = new Sprite("assets/gui/gameboy/Visor.png");
	private static Sprite dpad = new Sprite("assets/gui/gameboy/Dpad.png");
	
	public Gameboy() {
        addActor(new Actor(visor), 0, 0, 0);
        addActor(new Actor(roundButton, 3, 3), 475, 735, 1);
        addActor(new Actor(roundButton, 3, 3), 525, 685, 1);
        addActor(new Actor(longButton, 3, 3), 270, 770, 1);
		addActor(new Actor(longButton, 3, 3), 340, 770, 1);
		addActor(new Actor(dpad, 3, 3), 50, 660, 1);
	}
}
