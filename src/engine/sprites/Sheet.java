package engine.sprites;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sheet extends Sprite{
    private List<BufferedImage> imageSheet;
    private int frameDelay;
    private int frameCount;
    private int frameIndex;
    private int frameCounter;

    public Sheet(String imagePath, int spriteWidth, int spriteHeight, int frameDelay, int originX, int originY) {
        super(originX, originY);
        this.imageSheet = new ArrayList<>();
        this.frameDelay = frameDelay;
        this.frameIndex = 0;
        this.frameCounter = 0;

        try {
            BufferedImage spriteSheet = ImageIO.read(new File(imagePath));
            int sheetWidth = spriteSheet.getWidth();
            int sheetHeight = spriteSheet.getHeight();
            int rows = sheetHeight / spriteHeight;
            int cols = sheetWidth / spriteWidth;
            this.frameCount = rows * cols;
            for (int y = 0; y < rows; y++) 
                for (int x = 0; x < cols; x++) 
                    imageSheet.add(spriteSheet.getSubimage( x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight));
        } catch (IOException e) {
            System.err.println(imagePath);
            e.printStackTrace();
        }

        super.setImage(imageSheet.get(frameIndex));
    }
    
    @Override
    public void onTick() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            frameIndex++;
            if(frameIndex >= frameCount)
                frameIndex = 0;
            super.setImage(imageSheet.get(frameIndex));     
        }
        super.onTick();
    }
}