package game.actors.battle;

import java.awt.Color;

import engine.actors.Actor;
import engine.actors.Button;
import engine.actors.Room;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.datas.Move;


public class ActionMenu extends Room {
    private static final Sprite BACKGROUND = new Sprite.Builder().image("assets/gui/battle/ActionsBox.png").build();
    private final Button fightButton;
    private final Button switchButton;
    private final Button bagButton;
    private final Button runButton;
    private final Text text;

    public ActionMenu(Builder builder, Battle battle) {
        super(builder);
        this.text = new Text.Builder().build();
        this.fightButton = new Button.Builder().sprite(new Text.Builder().text("FIGHT").build()).build();
        this.switchButton = new Button.Builder().sprite(new Text.Builder().text("PKM").build()).build();
        this.bagButton = new Button.Builder().sprite(new Text.Builder().text("BALL").build()).build();
        this.runButton = new Button.Builder().sprite(new Text.Builder().text("RUN").build()).build();
        addActor(new Actor.Builder().sprite(BACKGROUND).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(this.text).build(), 20, 40, 0);
        addActor(this.fightButton, 290, 35, 0);
        addActor(this.switchButton, 410, 35, 0);
        addActor(this.runButton, 410, 80, 0);
        addActor(this.bagButton, 290, 80, 0);

        switchButton.setAction(() -> {
            this.setVisible(false);
            PartyMenu partyMenu = battle.getPartyMenu();
            partyMenu.setVisible(true);
        });
        fightButton.setAction(() -> {
            MovesMenu movesMenu = battle.getMovesMenu();
            movesMenu.setVisible(true);
            this.setVisible(false);
        });
        runButton.setAction(() -> {
            battle.exit();
        });
        bagButton.setAction(() -> {
            battle.capture();
        });
    }

    public void setText(String string) {
        text.setText(string);
    }

    public static String getIdleText(Pokemon pokemon) {
        return String.format("What will\n%s do?", pokemon.getStats().getName());
    }
}