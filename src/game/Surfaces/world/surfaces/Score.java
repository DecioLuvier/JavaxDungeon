package game.Surfaces.world.surfaces;

import engine.listeners.Actor;
import engine.listeners.Button;
import engine.listeners.Surface;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Surfaces.world.World;
import game.Surfaces.world.commands.GenerateIntro;
import game.Surfaces.world.commands.SaveGame;
import game.Surfaces.world.commands.SelectHint;

public class Score extends Surface{
    private static final Sprite backgroundSprite = new Sprite.Builder().image("assets/gui/world/Score.png").build();
    private static final Sprite starSprite = new Sprite.Builder().image("assets/gui/world/Star.png").build();
    private static final Sprite diskSprite = new Sprite.Builder().image("assets/gui/world/Disk.png").build();
    private static final Sprite arrowSprite = new Sprite.Builder().image("assets/gui/world/Arrow.png").build();
    private static final Sprite boxSprite = new Sprite.Builder().image("assets/gui/world/Box.png").build();

    private final World world;
    private final Text text;
    private final Actor box;
    private final Button hintButton;
    private final Button saveButton;
    private final Button exitButton;

    public Score(World world) {
        super(new Builder()); 
        this.world = world;
        this.box = new Actor.Builder().visible(false).sprite(boxSprite).scale(0.78, 0.78).build();
        this.hintButton = new Button.Builder().sprite(starSprite).action(new SelectHint(world, this)).build();
        this.saveButton = new Button.Builder().sprite(diskSprite).action(new SaveGame()).build();
        this.exitButton = new Button.Builder().sprite(arrowSprite).action(new GenerateIntro(world)).build();
        this.text = new Text.Builder().fontSize(19).build();

        add(new Actor.Builder().sprite(backgroundSprite).build(), 0, 0, 0);
        add(new Actor.Builder().sprite(text).build(), 260, 18, 1);
        
        add(box, 22, 21, 2);
        add(hintButton, 22, 21, 1);
        add(saveButton, 90, 20, 1);
        add(exitButton, 160, 20, 1);
    }

    public void update() {
        text.setText(String.format("Player: %dpts\nOpponent: %dpts", world.getPlayer().getScore(), world.getOpponent().getScore()));
        box.setVisible(world.isHintSelected());
    }
}
