package game.Surfaces.battle.commands;

import engine.Command;
import engine.listeners.Surface;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.surfaces.PartyMenu;

public class GeneratePartyMenu extends Command{
    private Surface surface;
    private Battle battle;

    public GeneratePartyMenu(Surface surface, Battle battle) {
        this.surface = surface;
        this.battle = battle;
    }

    @Override
    public void execute() {
        PartyMenu partyMenu = new PartyMenu(battle);
        battle.add(partyMenu, 13, 385, 0);
        battle.remove(surface);
        partyMenu.update();
    }
}
