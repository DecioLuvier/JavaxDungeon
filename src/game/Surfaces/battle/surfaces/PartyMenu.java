package game.Surfaces.battle.surfaces;

import java.awt.Color;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.Trainer;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.commands.GenerateActionMenu;
import game.Surfaces.battle.commands.SwapPokemon;

public class PartyMenu extends Surface {
    private static final Sprite BACKGROUND = new Sprite.Builder().image("assets/gui/battle/ActionsBox.png").build();
    private final Battle battle;
    private final Text text;
    private final Text[] slotTexts = new Text[5];
    private final Button[] slotButtons = new Button[5];
    private final Button exitButton;

    public PartyMenu(Battle battle) {
        super(new Builder());
        this.battle = battle;
        this.text = new Text.Builder().fontSize(22).color(Color.BLACK).text("Select a\nPokemon to\nswitch").build();
        this.exitButton = new Button.Builder().sprite(new Text.Builder().text("Exit").build()).action(new GenerateActionMenu(this, battle)).build();
        add(new Actor.Builder().sprite(BACKGROUND).build(), 0, 0, 0);
        add(new Actor.Builder().sprite(text).build(), 280, 25, 1);
        for (int i = 0; i < slotButtons.length; i++) {
            slotTexts[i] = new Text.Builder().fontSize(18).color(Color.BLACK).build();
            slotButtons[i] = new Button.Builder().sprite(slotTexts[i]).action(new SwapPokemon(battle, i + 1)).build();
            add(slotButtons[i], 17, 15 + (i * 23), 1);
        }

        add(exitButton, 420, 103, 1);
    }

    public void update() {
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