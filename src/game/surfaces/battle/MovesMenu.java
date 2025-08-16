package game.surfaces.battle;

import engine.Surface;
import engine.actors.Actor;
import engine.actors.Button;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.datas.Move;
import game.rooms.Battle;

import java.awt.Color;

public class MovesMenu extends Surface {
    private static final Sprite background = new Sprite("assets/gui/battle/Moves.png");
    private final Text energy = new Text("assets/fonts/FontPKMN.ttf", 20, Color.BLACK);
    private final Button exitButton = new Button(new Text("Exit", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK), null);
    private final Text[] moveTexts = new Text[3];
    private final Button[] moveButtons = new Button[3];

    public MovesMenu(Battle battle) {
        addActor(new Actor(background), 0, 0, 0);
        addActor(new Actor(energy), 20, 104, 0);

        for (int i = 0; i < moveButtons.length; i++) {
            final int idx = i;
            moveTexts[idx] = new Text("assets/fonts/FontPKMN.ttf", 20, Color.BLACK);
            moveButtons[idx] = new Button(moveTexts[idx], null);
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
                battle.getActionInfo().setTopText(attacker);
                battle.getMovesMenu().refresh(battle);
            });

            addActor(moveButtons[i], 20, 20 + (i * 28), 0);
        }

        addActor(exitButton, 420, 103, 0);
        exitButton.setAction(() -> {
            this.setVisible(false);
            battle.getActionMenu().setVisible(true);
        });

        refresh(battle);
        this.setVisible(false);
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
