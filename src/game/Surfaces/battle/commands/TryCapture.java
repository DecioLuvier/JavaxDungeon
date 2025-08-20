package game.Surfaces.battle.commands;

import engine.Command;
import game.Surfaces.battle.Battle;

public class TryCapture extends Command{
    Battle battle;

    public TryCapture(Battle battle){
        this.battle = battle;
    }

    @Override
    public void execute() {
        battle.capture();
    }
}
