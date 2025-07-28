package game.surfaces.menu;

import java.awt.Color;

import engine.Room;
import engine.Surface;
import engine.actors.TextActor;
import engine.actors.VisualActor;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;

public class Menu extends Surface {
    private static SpriteSheet score = new SpriteSheet("src/game/surfaces/menu/Score.png", 1, 0, 0, 0);
    private static SpriteSheet options = new SpriteSheet("src/game/surfaces/menu/Options.png", 1, 0, 0, 0);
    private static SpriteSheet team =  new SpriteSheet("src/game/surfaces/menu/Team.png", 1, 0, 0, 0);


    public Menu(int offsetX, int offsetY, int offsetZ) {
        super(offsetX, offsetY, offsetZ);
    }

    @Override
    public void onCreate(Room room) {
        create(room, new VisualActor(new Animation(score, 1, 1)), 0, 0, 1);
        create(room, new VisualActor(new Animation(options, 1, 1)), 0, 83, 1);
        create(room, new VisualActor(new Animation(team, 1, 1)), 0, 210, 1);


        create(room, new TextActor("PLAYER  10pts  TURN  03  OPPONENT  20pts", 16, "src/game/FontPKMN.ttf", new Color(0, 0, 0)), 16, 21, 5);
    }
}
