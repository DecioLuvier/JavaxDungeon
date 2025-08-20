package game.Surfaces.battle.commands;

import engine.Command;
import game.Pokemon;
import game.Surfaces.battle.Battle;
import game.Surfaces.battle.surfaces.ActionTexts;
import game.Surfaces.battle.surfaces.MovesMenu;
import game.datas.Move;

public class Attack extends Command{
    private Battle battle;
    private int index;
    private MovesMenu movesMenu;

    public Attack(Battle battle, int index, MovesMenu movesMenu){
        this.battle = battle;
        this.index = index;
        this.movesMenu = movesMenu;
    }

    @Override
    public void execute() {
        Pokemon attacker = battle.getMainPokemon();
        if (attacker.getCurrentCD() != 0) 
            return;
        Move move = null;
        if (index == 0)
            move = attacker.getStats().getFastMove();
        else if (index == 1) 
            move = attacker.getStats().getChargedMove();
        else if (index == 2) 
            move = attacker.getStats().getOptionalMove();
        if (move.isCharged() && attacker.getCurrentMP() < move.getEnergy()) 
            return;
        Battle.attack(attacker, battle.getOpponentPokemon(), move);
        battle.getOpponentPokemonInfo().setPokemon(battle.getOpponentPokemon());
        battle.getActionTexts().setTopText(ActionTexts.getActionText(attacker));
        movesMenu.update();
    }
    
}
