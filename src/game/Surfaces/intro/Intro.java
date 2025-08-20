package game.Surfaces.intro;

import java.awt.Color;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Animation;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Surfaces.intro.commands.GenerateCustomWorld;
import game.Surfaces.intro.commands.GenerateWorld;
import game.Surfaces.intro.commands.LoadGame;

public class Intro extends Surface {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    public Intro() {
        super(new Builder()); 

        Animation background = new Animation.Builder().imageSheet("assets/gui/intro/Intro.png")
                                                      .spriteWidth(531)
                                                      .spriteHeight(531)
                                                      .frameDelay(14)
                                                      .build();
        Sprite backgroundA = new Sprite.Builder().image("assets/gui/intro/ASD.png").build();

        this.button1 = new Button.Builder().sprite(new Text.Builder().text("New Game").build()).action(new GenerateWorld(this)).build();
        this.button2 = new Button.Builder().sprite(new Text.Builder().text("Custom Level").build()).action(new GenerateCustomWorld(this)).build();
        this.button3 = new Button.Builder().sprite(new Text.Builder().text("Continue Game").build()).action(new LoadGame()).build();
        this.button4 = new Button.Builder().sprite(new Text.Builder().text("Level Builder").color(Color.gray).build()).build();

        add(new Actor.Builder().sprite(background).build(), 0, 0, 0);
        add(new Actor.Builder().sprite(backgroundA).build(), 15, 415, 1);
        add(button1, 60, 440, 1);
        add(button2, 60, 475, 1);
        add(button3, 275, 440, 1);
        add(button4, 275, 475, 1);
    }
}