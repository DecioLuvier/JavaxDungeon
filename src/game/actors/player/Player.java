package game.actors.player;

import java.awt.event.KeyEvent;
import java.util.List;

import engine.Level;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.actors.world.box.Box;
import game.actors.world.floor.Floor;
import game.actors.world.wall.Wall;

public class Player extends BoardActor {
	private static SpriteSheet idle = new SpriteSheet("src/game/actors/player/Idle.png", 4, 4, 15, 15);
	private static SpriteSheet walk = new SpriteSheet("src/game/actors/player/Walk.png", 3, 3, 15, 17);
	private int idleTimer;

	public Player(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(idle, 3, 6, 6), row, col);
		idleTimer = 0;
	}

	@Override
	public void onReleasedKey(Level level, KeyEvent e) {
		BoardLevel boardLevel = (BoardLevel) level;
		int newRow = boardLevel.getRow(this);
		int newCol = boardLevel.getCol(this);

		switch (e.getKeyCode()) {
			case KeyEvent.VK_W -> newRow--;
			case KeyEvent.VK_S -> newRow++;
			case KeyEvent.VK_A -> newCol--;
			case KeyEvent.VK_D -> newCol++;
		}

		List<BoardActor> actors = boardLevel.getActors(newRow, newCol);
		Box box = boardLevel.getActor(actors, Box.class);
		if (box != null) {
			int newBoxRow = boardLevel.getRow(box);
			int newBoxCol = boardLevel.getCol(box);

			switch (e.getKeyCode()) {
				case KeyEvent.VK_W -> newBoxRow--;
				case KeyEvent.VK_S -> newBoxRow++;
				case KeyEvent.VK_A -> newBoxCol--;
				case KeyEvent.VK_D -> newBoxCol++;
			}

			if (canMoveTo(boardLevel, box, newBoxRow, newBoxCol))
				boardLevel.move(box, newBoxRow, newBoxCol);
		}

		if (canMoveTo(boardLevel, this, newRow, newCol)) {
			this.getAnimation().setSpriteSheet(walk);
			idleTimer = 10;

			if (boardLevel.getCol(this) - newCol < 0)
				this.getAnimation().setHorizontalOrientation(true);
			else if (boardLevel.getCol(this) - newCol > 0)
				this.getAnimation().setHorizontalOrientation(false);

			boardLevel.move(this, newRow, newCol);
		}
	}

	public static boolean canMoveTo(BoardLevel boardLevel, BoardActor boardActor, int row, int col) {
		List<BoardActor> actors = boardLevel.getActors(row, col);
		boolean hasFloor = boardLevel.getActor(actors, Floor.class) != null;
		boolean hasWall = boardLevel.getActor(actors, Wall.class) != null;
		boolean hasBox = boardLevel.getActor(actors, Box.class) != null;

		return hasFloor && !hasWall && !hasBox;
	}

	@Override
	public void onTick(Level level) {
		if (this.getAnimation().getSpriteSheet() == walk) {
			if (idleTimer == 0)
				this.getAnimation().setSpriteSheet(idle);
			idleTimer--;
		}

		super.onTick(level);
	}
}