package engine.sprites;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private BufferedImage image;
    private int originX;
    private int originY;

    protected Sprite(Builder builder) {
        this.image = builder.image;
        this.originX = builder.originX;
        this.originY = builder.originY;
    }

    public int getOriginX() { return originX; }
    public int getOriginY() { return originY; }
    public BufferedImage getImage() { return image; }
    public void setImage(BufferedImage image) { this.image = image; }
    public void onTick() {}

    public static class Builder {
        protected BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        protected int originX = 0;
        protected int originY = 0;

        public Builder image(String imagePath) {
            try {
                this.image = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                System.out.println("Failed to load image from path: " + imagePath + ". Reason: " + e.getMessage());
            }
            return this;
        }

        public Builder originX(int originX) {
            this.originX = originX;
            return this;
        }

        public Builder originY(int originY) {
            this.originY = originY;
            return this;
        }

        public Sprite build() {
            return new Sprite(this);
        }
    }
}
