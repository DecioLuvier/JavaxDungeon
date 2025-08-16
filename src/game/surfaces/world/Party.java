package game.surfaces.world;
import engine.Surface;
import engine.actors.Actor;
import engine.actors.Button;
import engine.sprites.Sprite;
import game.Pokemon;
import game.Trainer;
import game.rooms.World;

public class Party extends Surface {
    private final Actor selectionArrow = new Actor(new Sprite("assets/gui/world/Arrow.png"));
    private final Button[] pokemonSlots = new Button[6];
    private int selectedIndex;

    public Party(World world) {
        this.selectedIndex = -1;
        addActor(new Actor(new Sprite("assets/gui/world/Actions.png")), 0, 0, 0);
        addActor(selectionArrow, 0, 0, 2); 

        for (int i = 0; i < pokemonSlots.length; i++) {
            final int index = i;
            pokemonSlots[i] = new Button(null, () -> {
                Trainer trainer = world.getPlayer();
                if (selectedIndex == -1 && trainer.getPokemon(index) != null) {
                    selectedIndex = index;
                } else if (trainer.getPokemon(index) != null) {
                    trainer.swapPokemon(selectedIndex, index);
                    selectedIndex = -1;
                }
                update(world);
            });
            addActor(pokemonSlots[i], 14, 40 + (i * 60), 1);
        }
        update(world);
    }

    public void update(World world) {
        for (int i = 0; i < pokemonSlots.length; i++) {
            Pokemon pokemon = world.getPlayer().getPokemon(i);
            if (pokemon != null) {
                pokemonSlots[i].setSprite(pokemon.getStats().getSpriteSheet());
                pokemonSlots[i].setVisible(true);
            } else {
                pokemonSlots[i].setSprite(null);
                pokemonSlots[i].setVisible(false);
            }

            if (selectedIndex != -1) {
                selectionArrow.setVisible(true);
                selectionArrow.setPosition(this, 13, 40 + (selectedIndex * 60), 0);
            } else {
                selectionArrow.setVisible(false);
            }
        }
    }
}