package engine.sprites;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class Text extends Sprite {
    private String text;
    private Color color;
    private int size;
    private Font font;

    public Text(String text, String fontPath, int size, Color color) {
        this.text = text;
        this.color = color;
        this.size = size;
        setFont(fontPath);
    }
 
    public Text(String fontPath, int size, Color color) {
        this.text = " ";
        this.color = color;
        this.size = size;
        setFont(fontPath);
    }
    
    public void setText(String text) {
        this.text = text;
        updateImage();
    }

    public void setFont(String fontPath) {
        try {
            this.font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, (float) size);
        } catch (FontFormatException | IOException e) {
            this.font = new Font("Serif", Font.PLAIN, size);
            e.printStackTrace();
        }
        updateImage();
    }

    public void setFontSize(int size) {
        this.size = size;
        this.font = font.deriveFont(Font.PLAIN, (float) size);
        updateImage();
    }

    public void setColor(Color color) {
        this.color = color;
        updateImage();
    }

    private void updateImage() {
        BufferedImage tmp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tmp.createGraphics();
        g.setFont(font);
        int w = Math.max(1, g.getFontMetrics().stringWidth(this.text));
        int h = Math.max(1, g.getFontMetrics().getHeight());
        int ascent = g.getFontMetrics().getAscent();
        g.dispose();

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();
        g.setFont(font);
        g.setColor(color);
        g.drawString(this.text, 0, ascent);
        g.dispose();
        super.setImage(img);
    }
}
