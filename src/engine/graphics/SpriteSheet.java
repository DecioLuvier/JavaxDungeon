package engine.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private BufferedImage spriteSheet;
    private List<BufferedImage> frames;
    private int frameDelay;
    private int originX;
    private int originY;

    public SpriteSheet(String imagePath, int spriteCount, int frameDelay, int originX, int originY) {
        this.frames = new ArrayList<>();
        this.frameDelay = frameDelay;
        this.originX = originX;
        this.originY = originY;

        try {
            this.spriteSheet = ImageIO.read(new File(imagePath));
            int spriteSheetWidth = this.spriteSheet.getWidth() / spriteCount;
            int spriteSheetHeight = this.spriteSheet.getHeight();
            for (int col = 0; col < spriteCount; col++)
                this.frames
                        .add(spriteSheet.getSubimage(col * spriteSheetWidth, 0, spriteSheetWidth, spriteSheetHeight));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SpriteSheet(BufferedImage image) {
        this.frames = new ArrayList<>();
        this.frameDelay = 0;
        this.originX = 0;
        this.originY = 0;
        this.spriteSheet = image;
        this.frames.add(image);
    }

    public List<BufferedImage> getFrames() {
        return this.frames;
    }

    public BufferedImage getFrame(int index) {
        return this.frames.get(index);
    }

    public int getFrameDelay() {
        return this.frameDelay;
    }

    public double getOriginX() {
        return this.originX;
    }

    public double getOriginY() {
        return this.originY;
    }
}