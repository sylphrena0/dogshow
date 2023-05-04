package utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

import static java.awt.Font.createFont;

public class Utilities implements Parameters {

    /**
     * @param relativePath The relative path to the file
     * @return The URL of the file
     */
    public static URL getURL(String relativePath) {
        return Utilities.class.getResource(".." + pathSeparator + relativePath); //since Utilities is in a package, we need to go up one directory with ".."
    }

    /**
     * @param relativePath The relative path to the file
     * @return The path of the file. If the file does not exist, it will return the path of the parent directory
     * @throws RuntimeException If the file does not exist and there is no parent directory
     */
    public static String getPath(String relativePath) {
        try {
            return Objects.requireNonNull(getURL(relativePath.substring(0, relativePath.lastIndexOf(pathSeparator)))).toURI().getPath()  + relativePath.substring(relativePath.lastIndexOf(pathSeparator));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getFile(String relativePath) {
        return new File(getPath(relativePath));
    }

    /**
     * Gets a new icon from the images folder
     * @param name The name of the image in the images folder
     * @return The image icon
     */
    public static ImageIcon getImageIcon(String name) {
        return new ImageIcon(getURL("images" + pathSeparator + name));
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
            Font font = createFont(java.awt.Font.TRUETYPE_FONT, getFile("external" + pathSeparator + "Caveat-Regular.ttf"));
            return font.deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
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
