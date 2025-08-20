package game.Surfaces.battle.surfaces;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.commands.ExitBattle;
import game.Surfaces.battle.commands.GenerateFightMenu;
import game.Surfaces.battle.commands.GeneratePartyMenu;
import game.Surfaces.battle.commands.TryCapture;

public class ActionMenu extends Surface {
    private static final Sprite background = new Sprite.Builder().image("assets/gui/battle/ActionsBox.png").build();
    private final Battle battle;
    private final Button fightButton;
    private final Button switchButton;
    private final Button bagButton;
    private final Button runButton;
    private final Text text;

    public ActionMenu(Battle battle) {
        super(new Builder());
        this.battle = battle;
        this.text = new Text.Builder().build();
        this.fightButton = new Button.Builder().sprite(new Text.Builder().text("FIGHT").build()).action(new GenerateFightMenu(this, battle)).build();
        this.switchButton = new Button.Builder().sprite(new Text.Builder().text("PKM").build()).action(new GeneratePartyMenu(this, battle)).build();
        this.bagButton = new Button.Builder().sprite(new Text.Builder().text("BALL").build()).action(new TryCapture(battle)).build();
        this.runButton = new Button.Builder().sprite(new Text.Builder().text("RUN").build()).action(new ExitBattle(battle)).build();
        add(new Actor.Builder().sprite(background).build(), 0, 0, 0);
        add(new Actor.Builder().sprite(this.text).build(), 20, 40, 0);
        add(this.fightButton, 290, 35, 0);
        add(this.switchButton, 410, 35, 0);
        add(this.runButton, 410, 80, 0);
        add(this.bagButton, 290, 80, 0);
    }

    public void update() {
        text.setText(String.format("What will\n%s do?", battle.getMainPokemon().getStats().getName()));
    }
}