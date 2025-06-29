package game.actions;

import java.util.ArrayList;
import java.util.List;

import engine.graphics.SpriteSheet;
import game.Action;
import game.BoardActor;
import game.BoardLevel;

public class RandomMovement extends Action {
	private SpriteSheet idle;
	private SpriteSheet walk;

	public RandomMovement(int startFrames, int actionFrames, int endFrames, SpriteSheet idle, SpriteSheet walk) {
		super(startFrames, actionFrames, endFrames);
		this.idle = idle;
		this.walk = walk;
	}

	@Override
	public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
		int currentRow = boardLevel.getRow(boardActor);
		int currentCol = boardLevel.getCol(boardActor);

		List<int[]> validPositions = new ArrayList<>();
		int[][] directions = { { currentRow - 1, currentCol }, { currentRow + 1, currentCol }, { currentRow, currentCol - 1 }, { currentRow, currentCol + 1 } };

		for (int[] pos : directions)
			if (Move.canMoveTo(boardLevel, boardActor, pos[0], pos[1]))
				validPositions.add(pos);

		if (!validPositions.isEmpty()) {
			int[] chosenPosition = validPositions.get(boardLevel.getManager().getRandom().nextInt(validPositions.size()));
			boardActor.setAction(new Move(0, 20, 10, chosenPosition[0], chosenPosition[1], idle, walk));
		}
	}

	@Override
	public void onEnd(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.getAnimation().setSpriteSheet(idle);
		super.onEnd(boardLevel, boardActor);
	}
}