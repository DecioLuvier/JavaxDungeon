package engine.sprites;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Animation extends Sprite {
    private List<BufferedImage> imageSheet;
    private int frameDelay;
    private int frameIndex;
    private int frameCounter;

    protected Animation(Builder builder) {
        super(builder);
        this.imageSheet = new ArrayList<>();
        this.frameDelay = builder.frameDelay;
        this.frameIndex = 0;
        this.frameCounter = 0;

        try {
            BufferedImage spriteSheet = ImageIO.read(new File(builder.imagePath));
            int sheetWidth = spriteSheet.getWidth();
            int sheetHeight = spriteSheet.getHeight();
            int rows = sheetHeight / builder.spriteHeight;
            int cols = sheetWidth / builder.spriteWidth;
            for (int y = 0; y < rows; y++) 
                for (int x = 0; x < cols; x++) 
                    imageSheet.add(spriteSheet.getSubimage( x * builder.spriteWidth, y * builder.spriteHeight, builder.spriteWidth, builder.spriteHeight));
        } catch (IOException e) {
            System.err.println("Falha ao carregar spriteSheet: " + builder.imagePath);
            e.printStackTrace();
        }
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
