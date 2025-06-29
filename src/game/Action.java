package game;

public abstract class Action {
	private int startFrames;
	private int actionFrames;
	private int endFrames;

	public Action(int startFrames, int actionFrames, int endFrames) {
		this.startFrames = startFrames;
		this.actionFrames = startFrames + actionFrames;
		this.endFrames = startFrames + actionFrames + endFrames;
	}

	public int getStartFrames() {
		return this.startFrames;
	}

	public int getActionFrames() {
		return this.actionFrames;
	}

	public int getEndFrames() {
		return this.endFrames;
	}

	public void onStart(BoardLevel boardLevel, BoardActor boardActor) {
	}

	public void onAction(BoardLevel boardLevel, BoardActor boardActor) {
	}

	public void onEnd(BoardLevel boardLevel, BoardActor boardActor) {
		boardActor.setAction(null);
	}
}