package game.actors;

import engine.actors.Actor;
import engine.actors.Room;
import engine.sprites.Sprite;

public class Gameboy extends Room {
    private static final Sprite LONG_BUTTON = new Sprite.Builder().image("assets/gui/gameboy/Long.png").build();
    private static final Sprite ROUND_BUTTON = new Sprite.Builder().image("assets/gui/gameboy/Round.png").build();
    private static final Sprite VISOR = new Sprite.Builder().image("assets/gui/gameboy/Visor.png").build();
    private static final Sprite DPAD = new Sprite.Builder().image("assets/gui/gameboy/Dpad.png").build();

    public Gameboy(Builder builder) {
        super(builder);
        addActor(new Actor.Builder().sprite(VISOR).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(ROUND_BUTTON).scale(3.0, 3.0).build(), 475, 735, 1);
        addActor(new Actor.Builder().sprite(ROUND_BUTTON).scale(3.0, 3.0).build(), 525, 685, 1);
        addActor(new Actor.Builder().sprite(LONG_BUTTON).scale(3.0, 3.0).build(), 270, 770, 1);
        addActor(new Actor.Builder().sprite(LONG_BUTTON).scale(3.0, 3.0).build(), 340, 770, 1);
        addActor(new Actor.Builder().sprite(DPAD).scale(3.0, 3.0).build(), 50, 660, 1);
    }
}