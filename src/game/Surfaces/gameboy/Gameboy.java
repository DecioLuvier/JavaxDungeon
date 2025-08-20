package game.Surfaces.gameboy;

import engine.listeners.Actor;
import engine.listeners.Surface;
import engine.sprites.Sprite;

public class Gameboy extends Surface {
    private static final Sprite LONG_BUTTON = new Sprite.Builder().image("assets/gui/gameboy/Long.png").build();
    private static final Sprite ROUND_BUTTON = new Sprite.Builder().image("assets/gui/gameboy/Round.png").build();
    private static final Sprite VISOR = new Sprite.Builder().image("assets/gui/gameboy/Visor.png").build();
    private static final Sprite DPAD = new Sprite.Builder().image("assets/gui/gameboy/Dpad.png").build();

    public Gameboy() {
        super(new Builder());
        add(new Actor.Builder().sprite(VISOR).build(), 0, 0, 0);
        //add(new Actor.Builder().sprite(ROUND_BUTTON).scale(3.0, 3.0).build(), 475, 735, 1);
        //add(new Actor.Builder().sprite(ROUND_BUTTON).scale(3.0, 3.0).build(), 525, 685, 1);
        //add(new Actor.Builder().sprite(LONG_BUTTON).scale(3.0, 3.0).build(), 270, 770, 1);
        //add(new Actor.Builder().sprite(LONG_BUTTON).scale(3.0, 3.0).build(), 340, 770, 1);
        //add(new Actor.Builder().sprite(DPAD).scale(3.0, 3.0).build(), 50, 660, 1);
    }
}