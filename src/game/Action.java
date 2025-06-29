package game;

public abstract class Action {
	private int actionDelay;
	private int postActionDelay;
	private int endDelay;

	public Action(int actionDelay, int postActionDelay, int endDelay) {
		this.actionDelay = actionDelay + 1;
		this.postActionDelay = this.actionDelay + postActionDelay + 1;
		this.endDelay = this.actionDelay + this.postActionDelay + endDelay + 1;
	}

	public int getActionDelay() {
		return this.actionDelay;
	}

	public int getPostActionDelay() {
		return this.postActionDelay;
	}

	public int getEndDelay() {
		return this.endDelay;
	}

	public void onStart(BoardLevel boardLevel, BoardActor boardActor) {
	}

	public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
	}

	public void onPostAction(BoardLevel boardLevel, BoardActor boardActor) {
	}

	public void onEnd(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.setAction(null);
	}
}