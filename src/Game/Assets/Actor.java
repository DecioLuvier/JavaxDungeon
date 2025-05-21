package Game.Assets;

public class Actor {
    public String imagePath;
    public int depth;
    public int x;
    public int y;
    public boolean isVisible;

    public Actor(String imagePath, int depth, int x, int y) {
        this.imagePath = imagePath;
        this.depth = depth;
        this.x = x;
        this.y = y;
        this.isVisible = false;
    }
}