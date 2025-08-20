package game.Surfaces.world.commands;

import engine.Command;
import game.Surfaces.world.World;
import game.Surfaces.world.surfaces.Score;

public class SelectHint extends Command {
    private final World world;
    private final Score score;

    public SelectHint(World world, Score score) {
        this.world = world;
        this.score = score;
    }

    @Override
    public void execute() {
        if(world.getHintRemain() > 0)
            world.setHintSelected(!world.isHintSelected());
        score.update();
    }
}
