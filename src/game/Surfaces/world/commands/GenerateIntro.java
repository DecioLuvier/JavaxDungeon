package game.Surfaces.world.commands;

import engine.Command;
import engine.Manager;
import game.Surfaces.intro.Intro;
import game.Surfaces.world.World;

public class GenerateIntro extends Command {
    private final World world;

    public GenerateIntro(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        Intro intro = new Intro();
        Manager manager = Manager.get();
        manager.add(intro, 60, 60 ,1);
        manager.remove(world);
    }
}
