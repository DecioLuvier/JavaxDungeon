package game.surfaces.battle;

import java.awt.Color;

import engine.Surface;
import engine.actors.Actor;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.datas.Move;

public class ActionInfo extends Surface {
    private final static Sprite background = new Sprite("assets/gui/battle/AttackBox.png");
    private final Text topText = new Text("assets/fonts/FontPKMN.ttf", 19, Color.BLACK);
    private final Text bottomText = new Text("assets/fonts/FontPKMN.ttf", 19, Color.BLACK);

    public ActionInfo(Pokemon topPokemon, Pokemon bottomPokemon) {
        addActor(new Actor(background), 0, 0, 0);
        addActor(new Actor(this.topText), 18, 18, 0);
        addActor(new Actor(this.bottomText), 18, 46, 0);
        setTopText(topPokemon);
        setBottomText(bottomPokemon);
    }

    public void setTopText(Pokemon pokemon) {
        setAction(topText, pokemon);
    }

    public void setBottomText(Pokemon pokemon){
        setAction(bottomText, pokemon);
    }
    
    public static void setAction(Text text, Pokemon pokemon) {
        String pokemonName = pokemon.getStats().getName();
        int currentCD = pokemon.getCurrentCD();
        
        if (currentCD < 0) 
            text.setText(String.format("%s is waiting...", pokemonName));
        else if(currentCD > 0)
            text.setText(String.format("%s is resting...CD:%d", pokemonName, currentCD));
        else 
            text.setText(String.format("%s is gonna attack!", pokemonName));
    }

    public static void setAttack(Text text, Pokemon attacker, Move move, Pokemon target) {
        text.setText(String.format("%s used %s", attacker.getStats().getName(), move.getName()));
    }
}