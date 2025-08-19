package game.actors.battle;

import java.awt.Color;

import engine.actors.Actor;
import engine.actors.Button;
import engine.actors.Room;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.Trainer;

public class PartyMenu extends Room {
    private static final Sprite BACKGROUND = new Sprite.Builder().image("assets/gui/battle/ActionsBox.png").build();
    private final Text text;
    private final Text[] slotTexts = new Text[5];
    private final Button[] slotButtons = new Button[5];
    private final Button exitButton;

    public PartyMenu(Builder builder, Battle battle) {
        super(builder);
        this.text = new Text.Builder().fontSize(22).color(Color.BLACK).text("Select a\nPokemon to\nswitch").build();
        this.exitButton = new Button.Builder().sprite(new Text.Builder().fontSize(20).color(Color.BLACK).text("Exit").build()).action(null).build();
        addActor(new Actor.Builder().sprite(BACKGROUND).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(text).build(), 280, 25, 1);
        for (int i = 0; i < slotButtons.length; i++) {
            final int idx = i + 1;
            slotTexts[i] = new Text.Builder().fontSize(18).color(Color.BLACK).build();
            slotButtons[i] = new Button.Builder().sprite(slotTexts[i]).action(() -> battle.swap(idx)).build();
            addActor(slotButtons[i], 17, 15 + (i * 23), 1);
        }
        addActor(exitButton, 420, 103, 1);
        exitButton.setAction(() -> {
            this.setVisible(false);
            battle.getActionMenu().setVisible(true);
        });
    }

    public void refresh(Battle battle) {
        Trainer trainer = battle.getTrainer();
        for (int i = 0; i < slotTexts.length; i++) {
            Pokemon pokemon = trainer.getPokemon(i + 1); 
            if (pokemon == null) {
                slotTexts[i].setText("---");
                slotTexts[i].setColor(Color.GRAY);
            } else {
                String name = pokemon.getStats().getName();
                int currentHP = pokemon.getCurrentHP();
                int maxHP = pokemon.getHp();
                slotTexts[i].setText(name + " " + currentHP + "/" + maxHP);
                if (currentHP <= 0)
                    slotTexts[i].setColor(Color.GRAY);
                else
                    slotTexts[i].setColor(Color.BLACK);
            }
        }
    }
}