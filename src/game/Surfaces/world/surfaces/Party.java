package game.Surfaces.world.surfaces;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Sprite;
import game.Pokemon;
import game.Surfaces.world.World;
import game.Surfaces.world.commands.SwapCommand;

public class Party extends Surface{
    private static final Sprite partyBoxSprite = new Sprite.Builder().image("assets/gui/world/Actions.png").build();
    private static final Sprite boxSprite = new Sprite.Builder().image("assets/gui/world/Box.png").build();
    
    private final World world;
    private final Actor arrowActor;
    private final Button[] pokemonSlots;
    private int selectedIndex;

    public Party(World world) {
        super(new Builder()); 
        this.world = world;
        this.arrowActor = new Actor.Builder().sprite(boxSprite).visible(false).build();
        this.pokemonSlots = new Button[6];
        this.selectedIndex = -1;
        
        for (int i = 0; i < pokemonSlots.length; i++) {
            pokemonSlots[i] = new Button.Builder().action(new SwapCommand(world, this, i)).build();
            add(pokemonSlots[i], getX() + 13, getY() + 125 + (i * 60), getZ() + 2);
        }
        add(arrowActor);
        add(new Actor.Builder().sprite(partyBoxSprite).build(), 0, 83, 1);
    }

    public int getSelectedIndex() { return selectedIndex; }
    public void setSelectedIndex(int selectedIndex) { this.selectedIndex = selectedIndex; }

    public void update() {
        for (int i = 0; i < pokemonSlots.length; i++) {
            Pokemon pokemon = world.getPlayer().getPokemon(i);
            if (pokemon != null && pokemon.getStats() != null && pokemon.getStats().getSpriteSheet() != null) {
                pokemonSlots[i].setSprite(pokemon.getStats().getSpriteSheet());
                pokemonSlots[i].setVisible(true);
            } else {
                pokemonSlots[i].setSprite(null);
                pokemonSlots[i].setVisible(false);
            }

            if (selectedIndex != -1) {
                arrowActor.setVisible(true);
                arrowActor.setPosition(getX() + 13, getY() + 125 + (selectedIndex * 60), getZ() + 3);
            } else {
                arrowActor.setVisible(false);
            }
        }
    }
}
