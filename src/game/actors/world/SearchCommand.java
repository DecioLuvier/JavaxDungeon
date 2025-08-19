package game.actors.world;

import engine.Command;

public class SearchCommand implements Command {
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
        world.search(world.getPlayer(), row, col);
    }
}
 