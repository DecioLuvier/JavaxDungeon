package game.engine.actors;

import game.engine.Sprite;

public class VisualActor extends Actor {
    private Sprite sprite;
    private int x;
    private int y;

    public VisualActor(Sprite sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

}
