package engine.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Animation extends Sprite {
    private transient List<BufferedImage> imageSheet;
    private String imagePath;
    private int frameDelay;
    private int frameIndex;
    private int frameCounter;
    private int spriteWidth;
    private int spriteHeight;

    protected Animation(Builder builder) {
        super(builder);
        this.frameDelay = builder.frameDelay;
        this.imagePath = builder.imagePath;
        this.spriteWidth = builder.spriteWidth;
        this.spriteHeight = builder.spriteHeight;
        this.imageSheet = loadSpriteSheet(imagePath, spriteWidth, spriteHeight);
        this.frameIndex = 0;
        this.frameCounter = 0;
        super.setImage(imageSheet.get(frameIndex));
    }

    public static ArrayList<BufferedImage> loadSpriteSheet(String imagePath, int spriteWidth, int spriteHeight) {
        ArrayList<BufferedImage> frames = new ArrayList<>();
        BufferedImage spriteSheet = loadImage(imagePath);
        int sheetWidth = spriteSheet.getWidth();
        int sheetHeight = spriteSheet.getHeight();
        int rows = sheetHeight / spriteHeight;
        int cols = sheetWidth / spriteWidth;
        for (int y = 0; y < rows; y++) 
            for (int x = 0; x < cols; x++) 
                frames.add(spriteSheet.getSubimage( x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight));
        return frames;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); 
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();  
        this.imageSheet = loadSpriteSheet(imagePath, spriteWidth, spriteHeight);
        super.setImage(imageSheet.get(frameIndex));
    }

    @Override
    public void onTick() {
        frameCounter++;
        if (frameCounter >= frameDelay && imageSheet.size() > 0) {
            frameCounter = 0;
            frameIndex = (frameIndex + 1) % imageSheet.size();
            super.setImage(imageSheet.get(frameIndex));
        }
        super.onTick();
    }

    public static class Builder extends Sprite.Builder {
        private String imagePath;
        private int spriteWidth = 1;
        private int spriteHeight = 1;
        private int frameDelay = 6;

        public Builder imageSheet(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder spriteWidth(int spriteWidth) {
            this.spriteWidth = spriteWidth;
            return this;
        }

        public Builder spriteHeight(int spriteHeight) {
            this.spriteHeight = spriteHeight;
            return this;
        }

        public Builder frameDelay(int frameDelay) {
            this.frameDelay = frameDelay;
            return this;
        }

        @Override
        public Animation build() {
            return new Animation(this);
        }
    }
}
