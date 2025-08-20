package game.Surfaces.battle.commands;

import engine.Command;
import game.Surfaces.battle.Battle;


public class SwapPokemon extends Command{
    private final Battle battle;
    private final int index;
    
    public SwapPokemon(Battle battle, int index) {
        this.battle = battle;
        this.index = index;
    }

    @Override
    public void execute() {
        battle.swap(index);        
    }
}
