package engine.sprites;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sprite implements Serializable {
    private transient BufferedImage image;
    private String imagePath;
    private int originX;
    private int originY;

    protected Sprite(Builder builder) {
        this.image = builder.image;
        this.imagePath = builder.imagePath;
        this.originX = builder.originX;
        this.originY = builder.originY;
    }

    public int getOriginX() { return originX; }
    public int getOriginY() { return originY; }
    public BufferedImage getImage() { return image; }
    public String getImagePath() { return imagePath; }
    public void setImage(BufferedImage image) { this.image = image; }
    public void onTick() {}

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.image = loadImage(imagePath);
    }

    public static BufferedImage loadImage(String imagePath){
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            //System.err.println("Failed to load image from path: " + imagePath + ". Reason: " + e.getMessage());
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public static class Builder {
        protected BufferedImage image;
        protected String imagePath = "";
        protected int originX = 0;
        protected int originY = 0;

        public Builder image(String imagePath) {
            this.imagePath = imagePath;
            this.image = loadImage(imagePath);
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