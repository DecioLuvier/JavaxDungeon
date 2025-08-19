package game.actors.battle;

import engine.actors.Actor;
import engine.actors.Room;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;

public class PokemonInfo extends Room {
    // Static constants
    private static final Sprite BACKGROUND = new Sprite.Builder().image("assets/gui/battle/Bar.png").build();
    // UI elements
    private final Text nameText;
    private final Text levelText;
    private final Text hpText;

    public PokemonInfo(Builder builder) {
        super(builder);
        this.nameText = new Text.Builder().fontSize(24).build();
        this.levelText = new Text.Builder().fontSize(24).build();
        this.hpText = new Text.Builder().fontSize(19).build();

        addActor(new Actor.Builder().sprite(BACKGROUND).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(nameText).build(), 0, -28, 1);
        addActor(new Actor.Builder().sprite(levelText).build(), 210, -28, 1);
        addActor(new Actor.Builder().sprite(hpText).build(), 70, -5, 1);
    }

    public void setPokemon(Pokemon pokemon) {
        nameText.setText(pokemon.getStats().getName());
        levelText.setText(":" + pokemon.getLevel());
        hpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
    }
}