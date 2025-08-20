package game.Surfaces.world.commands;

import engine.Command;
import game.Trainer;
import game.Surfaces.world.World;
import game.Surfaces.world.surfaces.Party;

public class SwapCommand extends Command {
    private final World world;
    private final Party party;
    private final int index;

    public SwapCommand(World world, Party party, int index) {
        this.world = world;
        this.party = party;
        this.index = index;
    }

    @Override
    public void execute() {
        int selected = party.getSelectedIndex();
        Trainer trainer = world.getPlayer();
        if (selected == -1) {
            if (trainer.getPokemon(index) != null) {
                party.setSelectedIndex(index);
            }
        } else {
            trainer.swapPokemon(selected, index);
            party.setSelectedIndex(-1);
        }
        party.update();
    }
}
