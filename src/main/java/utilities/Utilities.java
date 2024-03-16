package utilities;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Objects;

import static java.awt.Font.createFont;

public class Utilities implements Parameters {

    private Utilities() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Gets a new icon from the images folder
     * @param name The name of the image in the images folder
     * @return The image icon
     */
    public static ImageIcon getImageIcon(String name) {
        return new ImageIcon(Objects.requireNonNull(Utilities.class.getClassLoader().getResource(name)));
    }

    /**
     * Gets a new scaled icon from the images folder
     * @param name   The name of the image in the images folder
     * @param width  The width of the image
     * @param height The height of the image
     * @return The scaled image
     */
    public static ImageIcon getScaledImageIcon(String name, int width, int height) {
        return new ImageIcon(getImageIcon(name).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    }

    /**
     * Gets the Caveat font from the external folder
     * @param style The style of the font
     * @param size  The size of the font
     * @return The font
     */
    public static Font getCaveatFont(int style, int size) {
        try {
            InputStream fontStream = Utilities.class.getClassLoader().getResourceAsStream("Caveat-Regular.ttf");
            assert fontStream != null;
            Font font = createFont(java.awt.Font.TRUETYPE_FONT, fontStream);
            fontStream.close();
            return font.deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            throw new MissingResourceException("Unable to load font!", "Utilities", "Caveat-Regular.ttf");
        }
    }

    /**
     * Calculates the relative height of the screen
     * @param percent The percent of the screen height to return
     * @return The percent of the screen height
     */
    public static int relativeHeight(double percent) {
        return (int) (screenSize.height * percent / 100);
    }

    /**
     * Calculates the relative width of the screen
     * @param percent The percent of the screen width to return
     * @return The percent of the screen width
     */
    public static int relativeWidth(double percent) {
        return (int) (screenSize.width * percent / 100);
    }

    /**
     * Letterboxes an ImageIcon
     * @param imageIcon The ImageIcon to letterbox
     * @param frameWidth The width of the frame
     * @param frameHeight The height of the frame
     * @return The letterboxed image
     */
    public static Image letterboxImage(ImageIcon imageIcon, int frameWidth, int frameHeight) {
        double imageWidth = imageIcon.getIconWidth();
        double imageHeight = imageIcon.getIconHeight();
        double scale = Math.min(frameWidth / imageWidth, frameHeight / imageHeight);
        int scaledWidth = (int) (imageWidth * scale);
        int scaledHeight = (int) (imageHeight * scale);
        return imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, java.awt.Image.SCALE_SMOOTH);
    }

}
