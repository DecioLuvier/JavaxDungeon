package game.engine;

public class Animation {
    private Sprite sprite;
    private double xScale;
    private double yScale;
    private int frameIndex;
    private int frameCounter;

    public Animation(Sprite sprite, double xScale, double yScale) {
        this.sprite = sprite;
        this.xScale = xScale;
        this.yScale = yScale;
        this.frameIndex = 0;
        this.frameCounter = 0;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.frameIndex = 0;
        this.frameCounter = 0;
    }

    public double getXScale() {
        return this.xScale;
    }

    public void setXScale(double xScale) {
        this.xScale = xScale;
    }

    public double getYScale() {
        return this.yScale;
    }

    public void setYScale(double yScale) {
        this.yScale = yScale;
    }

    public int getFrameIndex() {
        return this.frameIndex;
    }

    public void onTick() {
        frameCounter++;
        if (frameCounter >= this.sprite.getFrameDelay()) {
            frameCounter = 0;
            frameIndex = (frameIndex + 1) % this.sprite.getFrames().size();
        }
    }
}