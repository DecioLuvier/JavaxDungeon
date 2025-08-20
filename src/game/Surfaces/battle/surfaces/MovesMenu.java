package game.Surfaces.battle.surfaces;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.commands.Attack;
import game.Surfaces.battle.commands.GenerateActionMenu;
import game.datas.Move;

import java.awt.Color;

public class MovesMenu extends Surface {
    private static final Sprite BACKGROUND = new Sprite.Builder().image("assets/gui/battle/MovesBox.png").build();
    private final Battle battle;
    private final Text energy;
    private final Button exitButton;
    private final Text[] moveTexts = new Text[3];
    private final Button[] moveButtons = new Button[3];

    public MovesMenu(Battle battle) {
        super(new Builder());
        this.battle = battle;
        this.energy = new Text.Builder().build();
        this.exitButton = new Button.Builder().sprite(new Text.Builder().text("Exit").build()).action(new GenerateActionMenu(this, battle)).build();
        add(new Actor.Builder().sprite(BACKGROUND).build(), 0, 0, 0);
        add(new Actor.Builder().sprite(energy).build(), 20, 104, 0);
        for (int i = 0; i < moveButtons.length; i++) {
            final int idx = i;
            moveTexts[idx] = new Text.Builder().build();
            moveButtons[idx] = new Button.Builder().sprite(moveTexts[idx]).action(new Attack(battle, i, this)).build();
            add(moveButtons[i], 20, 20 + (i * 28), 0);
        }

        add(exitButton, 420, 103, 0);
    }

    public void update() {
        Pokemon attacker = battle.getMainPokemon();
        for (int i = 0; i < moveButtons.length; i++) {
            Move move = null;
            if (i == 0)
                move = attacker.getStats().getFastMove();
            else if (i == 1) 
                move = attacker.getStats().getChargedMove();
            else if (i == 2) 
                move = attacker.getStats().getOptionalMove();
            setButtonText(moveButtons[i], moveTexts[i], move, attacker);
        }
        energy.setText("Energy: " + attacker.getCurrentMP() + "Pts");
    }

    static private void setButtonText(Button button, Text text, Move move, Pokemon attacker) {
        String name = move.getName();
        String type = move.getType().getName();
        int energyCost = move.getEnergy();
        int currentMP = attacker.getCurrentMP();
        if (move.isCharged())
            text.setText(name + " / " + type + " -" + energyCost);
        else
            text.setText(name + " / " + type + " " + energyCost);
        if (move.isCharged() && currentMP < energyCost)
            text.setColor(Color.GRAY);
        else
            text.setColor(Color.BLACK);
    }
}