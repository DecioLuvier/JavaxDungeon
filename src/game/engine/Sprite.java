package game.engine;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Sprite {
    private String imagePath;
    private int renderOrder;
    private boolean visible;
    private boolean cameraRelative;

    public Sprite(String imagePath, int renderOrder) {
        this.imagePath = imagePath;
        this.renderOrder = renderOrder;
        this.cameraRelative = true;
        this.visible = true;
    }

    public Sprite(String imagePath, int renderOrder, boolean cameraRelative, boolean visible) {
        this.imagePath = imagePath;
        this.renderOrder = renderOrder;
        this.cameraRelative = cameraRelative;
        this.visible = visible;
    }

    public int getRenderOrder() {
        return this.renderOrder;
    }

    public Image getImage() {
        return new ImageIcon(this.imagePath).getImage();
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getCameraRelative() {
        return this.cameraRelative;
    }
}