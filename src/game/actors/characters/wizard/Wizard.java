package game.actors.characters.wizard;

import java.util.List;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.BoardActor;
import game.BoardLevel;
import game.States;
import game.actions.RandomMovement;
import game.actors.characters.player.Player;

public class Wizard extends BoardActor {
	private static SpriteSheet idle = new SpriteSheet("src/game/actors/characters/wizard/Idle.png", 5, 4, 5, 25);
	private static SpriteSheet walk = new SpriteSheet("src/game/actors/characters/wizard/Walk.png", 4, 3, 5, 25);
	private static SpriteSheet attack = new SpriteSheet("src/game/actors/characters/wizard/Attack.png", 7, 6, 5, 25);

	public Wizard(BoardLevel boardLevel, int row, int col) {
		super(boardLevel, new Animation(idle, 3, 5, 5), row, col, new States(true, false, false));
	}

	@Override
	public void onHasNoActions(BoardLevel boardLevel) {
		int currentRow = boardLevel.getRow(this);
		int currentCol = boardLevel.getCol(this);

		List<BoardActor> actorsInRow = boardLevel.getActorsInRow(currentRow);
		Player player = boardLevel.getActor(actorsInRow, Player.class);

		if (player != null) {
			if (currentCol > boardLevel.getCol(player))
				this.setAction(new Attack(false, idle, attack));
			else if (currentCol < boardLevel.getCol(player))
				this.setAction(new Attack(true, idle, attack));
		} else {
			this.setAction(new RandomMovement(10, 50, idle, walk));
		}

		super.onHasNoActions(boardLevel);
	}
}
