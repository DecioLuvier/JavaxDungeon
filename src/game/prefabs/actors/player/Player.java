package game.prefabs.actors.player;

import java.awt.event.KeyEvent;

import game.engine.Animation;
import game.engine.Level;
import game.engine.Sprite;
import game.engine.actors.BoardActor;
import game.prefabs.actors.floor.Floor;
import game.prefabs.actors.wall.Wall;

public class Player extends BoardActor {
	private static Sprite idle = new Sprite("src/game/prefabs/actors/player/Idle.png", 4, 4);
	private static Sprite walk = new Sprite("src/game/prefabs/actors/player/Walk.png", 3, 3);
	private int idleFrameCounter = 0;

	public Player(int row, int col) {
		super(new Animation(idle, 1, 1), 2, row, col);
	}

	public void onReleasedKey(Level level, KeyEvent e) {
		int newRow = this.getRow();
		int newCol = this.getCol();

		switch (e.getKeyCode()) {
			case KeyEvent.VK_W -> newRow--;
			case KeyEvent.VK_S -> newRow++;
			case KeyEvent.VK_A -> newCol--;
			case KeyEvent.VK_D -> newCol++;
		}

		this.getAnimation().setSprite(walk);

		switch (e.getKeyCode()) {
			case KeyEvent.VK_A -> this.getAnimation().setXScale(-1);
			case KeyEvent.VK_D -> this.getAnimation().setXScale(1);
		}

		if (!level.getActors(Floor.class, newRow, newCol).isEmpty() && level.getActors(Wall.class, newRow, newCol).isEmpty())
			this.setPosition(newRow, newCol);
	}

	@Override
	public void onTick(Level level) {
		if (this.getAnimation().getSprite() == walk) {
			idleFrameCounter++;
			if (idleFrameCounter >= 12) {
				idleFrameCounter = 0;
				this.getAnimation().setSprite(idle);
			}
		}
		super.onTick(level);
	}
}