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
	private int postActionDelay;
	private int endDelay;

	public RandomMovement(int postActionDelay, int endDelay, SpriteSheet idle, SpriteSheet walk) {
		super(0, 0, 0);
		this.endDelay = endDelay;
		this.postActionDelay = postActionDelay;
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
			boardActor.setAction(new Move(0, postActionDelay, endDelay, idle, walk, chosenPosition[0], chosenPosition[1]));
		}
	}
}