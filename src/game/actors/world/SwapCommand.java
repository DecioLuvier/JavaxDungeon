package game.actors.world;

import engine.Command;
import game.Trainer;

public class SwapCommand implements Command {
    private final World world;
    private final int index;

    public SwapCommand(World world, int index) {
        this.world = world;
        this.index = index;
    }

    @Override
    public void execute() {
        int selected = world.getSelectedIndex();
        Trainer trainer = world.getPlayer();
        if (selected == -1) {
            if (trainer.getPokemon(index) != null) {
                world.setSelectedIndex(index);
            }
        } else {
            trainer.swapPokemon(selected, index);
            world.setSelectedIndex(-1);
            
        }
        world.updateParty();
    }
}
