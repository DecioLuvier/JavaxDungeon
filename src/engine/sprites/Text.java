package engine.sprites;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class Text extends Sprite {
    private Color color;
    private Font font;
    private String text; 

    protected Text(Builder builder) {
        super(builder);
        this.color = builder.color;
        this.font = builder.font;
        this.text = builder.text; 
        setText(builder.text);
    }
    
    public void setText(String text) {
        this.text = text; 
        String[] lines = text.split("\n");
        BufferedImage tmp = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tmp.createGraphics();
        g.setFont(font);
        int maxWidth = 1;
        int lineHeight = g.getFontMetrics().getHeight();
        int ascent = g.getFontMetrics().getAscent();
        for (String line : lines) 
            maxWidth = Math.max(maxWidth, g.getFontMetrics().stringWidth(line));
        int totalHeight = Math.max(1, lineHeight * lines.length);
        g.dispose();
        BufferedImage img = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();
        g.setFont(font);
        g.setColor(color);
        for (int i = 0; i < lines.length; i++) 
            g.drawString(lines[i], 0, ascent + (i * lineHeight));
        g.dispose();
        super.setImage(img);
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        setText(this.text);
    }

    public String getText() {
        return this.text;
    }

    public static class Builder extends Sprite.Builder {
        private String text = " ";
        private Color color = Color.BLACK;
        private int size = 20;
        private Font font;

        public Builder() {
            try {
                this.font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/FontPKMN.ttf")).deriveFont(Font.PLAIN, (float) size);
            } catch (FontFormatException | IOException e) {
                System.err.println("Failed to load default font. Falling back to Serif: " + e.getMessage());
                this.font = new Font("Serif", Font.PLAIN, size);
            }
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder fontSize(int size) {
            this.size = size;
            this.font = font.deriveFont(Font.PLAIN, (float) size);
            return this;
        }

        public Builder fontPath(String fontPath) {
            try {
                this.font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, (float) size);
            } catch (FontFormatException | IOException e) {
                System.err.println("Failed to load font from path: " + fontPath + ". Reason: " + e.getMessage());
            }
            return this;
        }

        @Override
        public Text build() {
            return new Text(this);
        }
    }
}