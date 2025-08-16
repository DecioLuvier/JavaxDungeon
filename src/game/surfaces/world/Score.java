package game.surfaces.world;

import java.awt.Color;

import engine.Surface;
import engine.actors.Actor;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.rooms.World;

public class Score extends Surface {
    private final static Sprite background = new Sprite("assets/gui/world/Score.png");
    private final Text score  = new Text("assets/fonts/FontPKMN.ttf", 19, Color.BLACK);

    public Score(World world) {
        addActor(new Actor(background), 0, 0, 0);
        addActor(new Actor(score), 18, 18, 1);
        update(world);
    }

    public void update(World world) {
        score.setText(String.format("Player: %dpts Opponent: %dpts", world.getPlayer().getScore(), world.getOpponent().getScore()));
    }
}