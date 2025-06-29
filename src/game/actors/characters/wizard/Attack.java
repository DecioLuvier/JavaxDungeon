package game.actors.characters.wizard;

import engine.graphics.SpriteSheet;
import game.Action;
import game.BoardActor;
import game.BoardLevel;
import game.actors.world.fireball.Fireball;

public class Attack extends Action {
	private static int actionDelay = 0;
	private static int postActionDelay = 5;
	private static int endDelay = 10;
	private boolean attackToRight;
	private SpriteSheet idle;
	private SpriteSheet attack;

	public Attack(boolean attackToRight, SpriteSheet idle, SpriteSheet attack) {
		super(actionDelay, postActionDelay, endDelay);
		this.attackToRight = attackToRight;
		this.idle = idle;
		this.attack = attack;
	}

	@Override
	public void onStart(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.getAnimation().setSpriteSheet(attack);
		boardActor.getAnimation().setHorizontalOrientation(attackToRight);
		super.onStart(boardLevel, boardActor);
	}

	@Override
	public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
		int currentRow = boardLevel.getRow(boardActor);
		int currentCol = boardLevel.getCol(boardActor);
		if (this.attackToRight)
			currentCol++;
		else
			currentCol--;

		boardLevel.spawn(new Fireball(boardLevel, currentRow, currentCol, attackToRight));
		super.onAction(boardLevel, boardActor);
	}

	@Override
	public void onEnd(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.getAnimation().setSpriteSheet(idle);
		super.onEnd(boardLevel, boardActor);
	}

}
