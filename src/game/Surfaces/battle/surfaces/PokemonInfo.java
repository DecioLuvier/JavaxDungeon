package game.Surfaces.battle.surfaces;

import engine.listeners.Actor;
import engine.listeners.Surface;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;

public class PokemonInfo extends Surface {
    private static final Sprite background = new Sprite.Builder().image("assets/gui/battle/Bar.png").build();
    private final Text nameText;
    private final Text levelText;
    private final Text hpText;

    public PokemonInfo() {
        super(new Builder());
        this.nameText = new Text.Builder().fontSize(24).build();
        this.levelText = new Text.Builder().fontSize(24).build();
        this.hpText = new Text.Builder().fontSize(19).build();

        add(new Actor.Builder().sprite(background).build(), 0, 0, 0);
        add(new Actor.Builder().sprite(nameText).build(), 0, -28, 1);
        add(new Actor.Builder().sprite(levelText).build(), 210, -28, 1);
        add(new Actor.Builder().sprite(hpText).build(), 70, -5, 1);
    }

    public void setPokemon(Pokemon pokemon) {
        nameText.setText(pokemon.getStats().getName());
        levelText.setText(":" + pokemon.getLevel());
        hpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
    }
}