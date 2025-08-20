package game.Surfaces.world.commands;

import engine.Command;
import game.Surfaces.world.World;

public class SearchCommand extends Command {
    private final World world;
    private final int row;
    private final int col;

    public SearchCommand(World world, int row, int col) {
        this.world = world;
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        if(world.isHintSelected())
            world.hint(row, col);
        else
            world.search(world.getPlayer(), row, col);
    }
}
 