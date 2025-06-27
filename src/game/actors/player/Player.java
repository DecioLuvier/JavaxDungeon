package game.actors.player;

import java.awt.event.KeyEvent;

import engine.Level;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.actions.Move;

public class Player extends BoardActor {
	private static SpriteSheet idle = new SpriteSheet("src/game/actors/player/Idle.png", 4, 4, 15, 15);
	private static SpriteSheet walk = new SpriteSheet("src/game/actors/player/Walk.png", 3, 3, 15, 20);

	public Player(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(idle, 3, 6, 6), row, col);
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

		this.setAction(new Move(0, 5, 10, newRow, newCol, idle, walk));
	}
}