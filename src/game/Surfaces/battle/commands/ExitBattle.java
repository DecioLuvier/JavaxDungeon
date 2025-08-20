package game.Surfaces.battle.commands;

import engine.Command;
import game.Surfaces.battle.Battle;

public class ExitBattle extends Command{
    Battle battle;

    public ExitBattle(Battle battle){
        this.battle = battle;
    }
    
    @Override
    public void execute() {
        battle.exit();
    }
}
