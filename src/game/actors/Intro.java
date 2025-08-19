package game.actors;

import java.awt.Color;

import engine.Manager;
import engine.actors.Actor;
import engine.actors.Button;
import engine.actors.Room;
import engine.sprites.Animation;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.Trainer;
import game.actors.world.World;
import game.datas.Pokedex;

public class Intro extends Room {
    private final Button button1;
    private final Button button2;
    private final Button button3;
    private final Button button4;

    public Intro(Builder builder) {
        super(builder);

        Animation background = new Animation.Builder().imageSheet("assets/gui/intro/Intro.png")
                                                      .spriteWidth(531)
                                                      .spriteHeight(531)
                                                      .frameDelay(14)
                                                      .build();
        Sprite backgroundA = new Sprite.Builder().image("assets/gui/intro/ASD.png").build();
        Text newGameText = new Text.Builder().text("New Game").build();
        Text customLevelText = new Text.Builder().color(Color.GRAY).text("Custom Level").build();
        Text continueGameText = new Text.Builder().text("Continue Game").build();
        Text levelBuilderText = new Text.Builder().color(Color.GRAY).text("Level Builder").build();

       this.button1 = new Button.Builder().sprite(newGameText).action(() -> {
            Trainer player = new Trainer(false);
            player.addPokemon(Pokemon.generateBasePokemon(null));
            Trainer opponent = new Trainer(true);
            opponent.addPokemon(Pokemon.generateBasePokemon(null));
            World world = new World(new Room.Builder(), player, opponent, this);
            Manager.get().getRoom().addActor(world,59, 59 ,1);
            this.setVisible(false);
        }).build();
        
        this.button2 = new Button.Builder().sprite(customLevelText).build();
        this.button3 = new Button.Builder().sprite(continueGameText).build();
        this.button4 = new Button.Builder().sprite(levelBuilderText).build();

        addActor(new Actor.Builder().sprite(background).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(backgroundA).build(), 15, 415, 1);
        addActor(button1, 60, 440, 1);
        addActor(button2, 60, 475, 1);
        addActor(button3, 275, 440, 1);
        addActor(button4, 275, 475, 1);
    }
}