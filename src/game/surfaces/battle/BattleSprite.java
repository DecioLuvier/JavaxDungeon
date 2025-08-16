package game.surfaces.battle;

import engine.Surface;
import engine.actors.Actor;
import game.Pokemon;

public class BattleSprite extends Surface {
    private final Actor actor = new Actor(null);

    public BattleSprite(Pokemon pokemon, boolean flipHorizontally) {
        addActor(actor, 0, 0, 0);
        actor.setYScale(4);
        if(flipHorizontally)
            actor.setXScale(-4);
        else
           actor.setXScale(4);

        setPokemon(pokemon);
    }

    public void setPokemon(Pokemon pokemon) {
        actor.setSprite(pokemon.getStats().getSpriteSheet());
    }
}