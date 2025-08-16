package game.surfaces.battle;

import java.awt.Color;

import engine.Surface;
import engine.actors.Actor;
import engine.actors.Button;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.Trainer;
import game.rooms.Battle;

public class PartyMenu extends Surface {
    private static final Sprite background = new Sprite("assets/gui/battle/Actions.png");

    private final Text topText = new Text("Select a", "assets/fonts/FontPKMN.ttf", 22, Color.BLACK);
    private final Text midleText = new Text("Pokemon to", "assets/fonts/FontPKMN.ttf", 22, Color.BLACK);
    private final Text bottomText = new Text("switch", "assets/fonts/FontPKMN.ttf", 22, Color.BLACK);
    private final Text[] slotTexts = new Text[5];
    private final Button[] slotButtons = new Button[5];

    private final Button exitButton = new Button(new Text("Exit", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK), null);

    public PartyMenu(Battle battle) {
        addActor(new Actor(background), 0, 0, 0);
        addActor(new Actor(topText), 280, 25, 1);
        addActor(new Actor(midleText), 280, 56, 1);
        addActor(new Actor(bottomText), 280, 85, 1);

        for (int i = 0; i < slotButtons.length; i++) {
            final int idx = i + 1;
            slotTexts[i] = new Text("assets/fonts/FontPKMN.ttf", 18, Color.BLACK);
            slotButtons[i] = new Button(slotTexts[i], () -> battle.swap(idx));
            addActor(slotButtons[i], 17, 15 + (i * 23), 1);
        }

        addActor(exitButton, 420, 103, 1);
        exitButton.setAction(() -> {
            this.setVisible(false);
            battle.getActionMenu().setVisible(true);
        });

        refresh(battle);
        this.setVisible(false);
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
