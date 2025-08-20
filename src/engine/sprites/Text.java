package engine.sprites;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.awt.FontFormatException;

public class Text extends Sprite {
    private transient Font font;
    private Color color;
    private String text; 
    private int size;
    private String fontPath;


    protected Text(Builder builder) {
        super(builder);
        this.color = builder.color;
        this.fontPath = builder.fontPath;
        this.size = builder.size;
        this.font = loadFont(fontPath, size);
        this.text = builder.text; 
        update();
    }
    
    private void update(){
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

    public void setText(String text) {
        this.text = text; 
        update();
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        update();
    }

    public static Font loadFont(String fontPath, int fontSize) {
        try {
            File fontFile = new File(fontPath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile); 
            return customFont.deriveFont(Font.PLAIN, (float) fontSize);
        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load default font. Falling back to Serif: " + e.getMessage());
            return new Font("Serif", Font.PLAIN, fontSize);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.font = loadFont(fontPath, size);
        update();
    }


    public static class Builder extends Sprite.Builder {
        private String text = " ";
        private Color color = Color.BLACK;
        private int size = 20;
        private String fontPath = "assets/fonts/FontPKMN.ttf";

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
            return this;
        }

        public Builder fontPath(String fontPath) {
            this.fontPath = fontPath;
            return this;
        }

        @Override
        public Text build() {
            return new Text(this);
        }
    }
}