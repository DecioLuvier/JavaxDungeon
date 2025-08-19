package game.actors.battle;

import engine.actors.Actor;
import engine.actors.Button;
import engine.actors.Room;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.datas.Move;

import java.awt.Color;

public class MovesMenu extends Room {
    private static final Sprite BACKGROUND = new Sprite.Builder().image("assets/gui/battle/MovesBox.png").build();
    private final Text energy;
    private final Button exitButton;
    private final Text[] moveTexts = new Text[3];
    private final Button[] moveButtons = new Button[3];

    public MovesMenu(Builder builder, Battle battle) {
        super(builder);
        this.energy = new Text.Builder().build();
        this.exitButton = new Button.Builder().sprite(new Text.Builder().text("Exit").build()).build();
        addActor(new Actor.Builder().sprite(BACKGROUND).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(energy).build(), 20, 104, 0);
        for (int i = 0; i < moveButtons.length; i++) {
            final int idx = i;
            moveTexts[idx] = new Text.Builder().build();
            moveButtons[idx] = new Button.Builder().sprite(moveTexts[idx]).build();
        }
        for (int i = 0; i < moveButtons.length; i++) {
            final int idx = i;
            moveButtons[i].setAction(() -> {
                Pokemon attacker = battle.getTrainer().getPokemon(0);
                if (attacker.getCurrentCD() != 0) 
                    return;
                Move move = null;
                if (idx == 0)
                    move = attacker.getStats().getFastMove();
                else if (idx == 1) 
                    move = attacker.getStats().getChargedMove();
                else if (idx == 2) 
                    move = attacker.getStats().getOptionalMove();
                if (move.isCharged() && attacker.getCurrentMP() < move.getEnergy()) 
                    return;
                Battle.attack(attacker, battle.getOpponentPokemon(), move);
                battle.getOpponentPokemonInfo().setPokemon(battle.getOpponentPokemon());
                battle.getActionTexts().setTopText(ActionTexts.getActionText(attacker));
                refresh(battle);
            });
            addActor(moveButtons[i], 20, 20 + (i * 28), 0);
        }
        addActor(exitButton, 420, 103, 0);
        exitButton.setAction(() -> {
            this.setVisible(false);
            battle.getActionMenu().setVisible(true);
        });
    }

    public void refresh(Battle battle) {
        Pokemon attacker = battle.getTrainer().getPokemon(0);
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