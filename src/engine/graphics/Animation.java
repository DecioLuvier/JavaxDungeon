package engine.graphics;

public class Animation {
    private SpriteSheet spriteSheet;
    private double xScale;
    private double yScale;
    private int frameIndex;
    private int frameCounter;

    public Animation(SpriteSheet spriteSheet, double xScale, double yScale) {
        this.spriteSheet = spriteSheet;
        this.xScale = xScale;
        this.yScale = yScale;
        this.frameIndex = 0;
        this.frameCounter = 0;
    }

    public int getFrameIndex() {
        return this.frameIndex;
    }

    public double getXScale() {
        return this.xScale;
    }

    public double getYScale() {
        return this.yScale;
    }

    public void setXScale(double xScale) {
        this.xScale = xScale;
    }

    public void setYScale(double yScale) {
        this.yScale = yScale;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        this.frameIndex = 0;
        this.frameCounter = 0;
    }

    public SpriteSheet getSpriteSheet() {
        return this.spriteSheet;
    }

    public void setHorizontalOrientation(boolean faceRight) {
        if (faceRight)
            this.xScale = Math.abs(this.xScale);
        else
            this.xScale = -Math.abs(this.xScale);
    }

    public void setVerticalOrientation(boolean faceUp) {
        if (faceUp)
            this.yScale = -Math.abs(this.yScale);
        else
            this.yScale = Math.abs(this.yScale);
    }

    public void onTick() {
        frameCounter++;
        if (frameCounter >= this.spriteSheet.getFrameDelay()) {
            frameCounter = 0;
            frameIndex = (frameIndex + 1) % this.spriteSheet.getFrames().size();
        }
    }
}
