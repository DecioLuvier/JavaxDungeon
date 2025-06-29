package game.actions;

import engine.graphics.SpriteSheet;
import game.Action;
import game.BoardActor;
import game.BoardLevel;
import game.actors.world.floor.Floor;

import java.util.List;

public class DestroyActors extends Action {
	private SpriteSheet idle;
	private SpriteSheet attack;
	private boolean selfDestroy;
	private boolean groundDestroy;

	public DestroyActors(int actionDelay, int postActionDelay, int endDelay, SpriteSheet idle, SpriteSheet Attack, boolean selfDestroy, boolean groundDestroy) {
		super(actionDelay, postActionDelay, endDelay);
		this.idle = idle;
		this.attack = Attack;
		this.selfDestroy = selfDestroy;
		this.groundDestroy = groundDestroy;
	}

	@Override
	public void onStart(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.getAnimation().setSpriteSheet(attack);
		super.onStart(boardLevel, boardActor);
	}

	@Override
	public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
		List<BoardActor> othersBoardActors = boardLevel.getActors(boardLevel.getRow(boardActor), boardLevel.getCol(boardActor));

		for (BoardActor otherBoardActor : othersBoardActors)
			if (groundDestroy || !(otherBoardActor instanceof Floor))
				if (selfDestroy || otherBoardActor != boardActor)
					boardLevel.despawn(otherBoardActor);

		super.onAction(boardLevel, boardActor);
	}

	@Override
	public void onPostAction(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.getAnimation().setSpriteSheet(idle);
		super.onPostAction(boardLevel, boardActor);
	}
}