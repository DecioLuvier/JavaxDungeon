package game.Surfaces.battle.commands;

import engine.Command;
import engine.listeners.Surface;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.surfaces.ActionMenu;

public class GenerateActionMenu extends Command{
    private Surface surface;
    private Battle battle;

    public GenerateActionMenu(Surface surface, Battle battle) {
        this.surface = surface;
        this.battle = battle;
    }

    @Override
    public void execute() {
        ActionMenu movesMenu = new ActionMenu(battle);
        battle.add(movesMenu, 13, 385, 0);
        battle.remove(surface);
        movesMenu.update();
    }
}
