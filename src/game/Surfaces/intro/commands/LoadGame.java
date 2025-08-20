package game.Surfaces.intro.commands;

import engine.Command;
import engine.Manager;

public class LoadGame extends Command{
    @Override
    public void execute() {
        Manager.loadState();
    }
    
}
