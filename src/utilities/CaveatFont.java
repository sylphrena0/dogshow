package utilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Font.createFont;

public class CaveatFont {
    public static Font getFont(int style, int size) {
        try {
            Font font = createFont(java.awt.Font.TRUETYPE_FONT, new File("Caveat-Regular.ttf"));
            return font.deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
