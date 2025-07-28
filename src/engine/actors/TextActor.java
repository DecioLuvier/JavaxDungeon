package engine.actors;

import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class TextActor extends VisualActor {
    private String text;
    private Color textColor;
    private Font customFont;

    public TextActor(String text, int fontSize, String fontFileName, Color textColor) {
        super(null);
        this.text = text;
        this.textColor = textColor;

        try {
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName)).deriveFont(Font.PLAIN, (float) fontSize);
        } catch (FontFormatException | IOException e) {
            this.customFont = new Font("Serif", Font.PLAIN, fontSize);
            e.printStackTrace();
        }

        setText(text);
    }

    public TextActor(int fontSize, String fontFileName, Color textColor) {
        super(null);
        this.text = "";
        this.textColor = textColor;

        try {
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName)).deriveFont(Font.PLAIN, (float) fontSize);
        } catch (FontFormatException | IOException e) {
            this.customFont = new Font("Serif", Font.PLAIN, fontSize);
            e.printStackTrace();
        }
    }


    public void setText(String text) {
        // Handle null or empty text
        if (text == null || text.isEmpty()) {
            text = " "; // Use a single space as fallback to ensure non-zero width
        }
        this.text = text; // Update the instance variable

        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tempImage.createGraphics();
        g.setFont(customFont);

        int textWidth = g.getFontMetrics().stringWidth(text); // Use the new text
        int textHeight = g.getFontMetrics().getHeight();
        g.dispose();

        // Ensure minimum width to avoid BufferedImage error
        if (textWidth <= 0) {
            textWidth = 1;
        }

        BufferedImage textImage = new BufferedImage(textWidth, textHeight, BufferedImage.TYPE_INT_ARGB);
        g = textImage.createGraphics();

        g.setFont(customFont);
        g.setColor(textColor);
        g.setBackground(new Color(0, 0, 0, 0)); 
        g.clearRect(0, 0, textWidth, textHeight);
        g.drawString(text, 0, g.getFontMetrics().getAscent()); // Use the new text
        g.dispose();

        SpriteSheet spriteSheet = new SpriteSheet(textImage);
        this.setAnimation(new Animation(spriteSheet, 1, 1));
    }

}