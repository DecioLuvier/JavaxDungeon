package game.surfaces.battle;

import java.awt.Color;

import engine.Surface;
import engine.actors.Actor;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;

public class PokemonInfo extends Surface {
    private final static Sprite background = new Sprite("assets/gui/battle/Bar.png");
    private final Text mainNameText = new Text("assets/fonts/FontPKMN.ttf", 24, Color.BLACK);
    private final Text mainLevelText = new Text("assets/fonts/FontPKMN.ttf", 24, Color.BLACK);
    private final Text mainHpText = new Text("assets/fonts/FontPKMN.ttf", 19, Color.BLACK);

    public PokemonInfo(Pokemon pokemon) {
        addActor(new Actor(background), 0, 0, 0);
        addActor(new Actor(this.mainNameText), 0, -28, 0);
        addActor(new Actor(this.mainLevelText), 210, -28, 0);
        addActor(new Actor(this.mainHpText), 70, -5, 0);
        setPokemon(pokemon);
    }

    public void setPokemon(Pokemon pokemon){
        mainNameText.setText(pokemon.getStats().getName());
        mainLevelText.setText(":" + pokemon.getLevel());
        mainHpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
    }
}
