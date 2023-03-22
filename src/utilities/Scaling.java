package utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scaling implements ConfigParameters {
    public static int relativeHeight(double percent) {
        return (int) (screenSize.height * percent/100);
    }

    public static int relativeWidth(double percent) {
        return (int) (screenSize.width * percent/100);
    }

    public static double heightToWidth(double percent) {
        return 100 * (((percent / 100.0) * (double) (screenSize.height)) / (double) (screenSize.width));
    }

    public static double widthToHeight(double percent) {
        return 100 * ((percent/100.0) * (double) (screenSize.width) / (double) (screenSize.height));
    }

    /**
     * Letterboxing method from https://stackoverflow.com/questions/10245220/resize-image-maintain-aspect-ratio
     * @param image
     * @param frame
     * @return
     */
    public static Dimension letterboxImage(Dimension image, Dimension frame) {
        int original_width = image.width;
        int original_height = image.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > frame.width) {
            //scale width to fit
            new_width = frame.width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > frame.height) {
            //scale height to fit instead
            new_height = frame.height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }


}
