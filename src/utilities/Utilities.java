package utilities;

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

    public static String getPath(String relativePath) {
        try {
            return Objects.requireNonNull(Utilities.class.getResource(".." + pathSeparator + relativePath)).toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getFile(String relativePath) {
        return new File(getPath(relativePath));
    }

    public static ImageIcon getImageIcon(String name) {
        return new ImageIcon(getURL("images" + pathSeparator + name));
    }

    /**
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
     * @param percent The percent of the screen height to return
     * @return The percent of the screen height
     */
    public static int relativeHeight(double percent) {
        return (int) (screenSize.height * percent / 100);
    }

    /**
     * @param percent The percent of the screen width to return
     * @return The percent of the screen width
     */
    public static int relativeWidth(double percent) {
        return (int) (screenSize.width * percent / 100);
    }

    /**
     * @param image The image to be scaled
     * @param frame The frame to scale the image within
     * @return The scaled image
     */
    public static Dimension letterboxImage(Dimension image, Dimension frame) {
        double aspectRatio = (double) image.width / (double) image.height;
        System.out.println(aspectRatio);
        return aspectRatio >= 1 ? new Dimension(frame.width, (int) (frame.height / aspectRatio)) : new Dimension((int) (frame.width * aspectRatio), frame.height);
    }

}
