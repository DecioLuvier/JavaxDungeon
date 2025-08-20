package game.Surfaces.battle.commands;

import engine.Command;
import engine.listeners.Surface;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.surfaces.MovesMenu;

public class GenerateFightMenu extends Command{
    private Surface surface;
    private Battle battle;

    public GenerateFightMenu(Surface surface, Battle battle) {
        this.surface = surface;
        this.battle = battle;
    }

    @Override
    public void execute() {
        MovesMenu movesMenu = new MovesMenu(battle);
        battle.add(movesMenu, 13, 385, 0);
        battle.remove(surface);
        movesMenu.update();
    }
}
