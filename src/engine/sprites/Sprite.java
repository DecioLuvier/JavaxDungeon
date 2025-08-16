package engine.sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
    private BufferedImage image;
    private final int originX;
    private final int originY;

    public Sprite(String imagePath, int originX, int originY) {
        setImage(imagePath);
        this.originX = originX;
        this.originY = originY;
    }

    public Sprite(String imagePath) {
        setImage(imagePath);
        this.originX = 0;
        this.originY = 0;
    }

    public Sprite(int originX, int originY) {
        this.image = null;
        this.originX = originX;
        this.originY = originY;
    }

    public Sprite() {
        this.image = null;
        this.originX = 0;
        this.originY = 0;
    }


    public int getOriginX() { return originX; }
    public int getOriginY() { return originY; }
    public BufferedImage getImage() { return image; }

    public void setImage(BufferedImage image) { this.image = image; }
    public void setImage(String imagePath) {
        try {
            this.image = ImageIO.read(new File(imagePath)); 
        } catch (IOException e) {
            e.printStackTrace();
            this.image = null;
        }
    }

    public void onTick() { }
}
