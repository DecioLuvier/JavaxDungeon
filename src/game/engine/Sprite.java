package game.engine;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private BufferedImage spriteSheet;
    private List<BufferedImage> frames;
    private int frameDelay;

    public Sprite(String imagePath, int spriteCount, int frameDelay) {
        this.frames = new ArrayList<>();
        this.frameDelay = frameDelay;

        try {
            this.spriteSheet = ImageIO.read(new File(imagePath));
            int spriteSheetWidth = this.spriteSheet.getWidth() / spriteCount;
            int spriteSheetHeight = this.spriteSheet.getHeight();
            for (int col = 0; col < spriteCount; col++)
                this.frames.add(spriteSheet.getSubimage(col * spriteSheetWidth, 0, spriteSheetWidth, spriteSheetHeight));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}