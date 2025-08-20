package game.Surfaces.world.commands;

import engine.Command;
import engine.Manager;

public class SaveGame extends Command {
    @Override
    public void execute() {
        Manager.get().saveState();
    }
    
}
