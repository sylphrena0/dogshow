package utilities;

import java.awt.*;

public class Scaling implements ConfigParameters {
    public static int relativeHeight(double percent) {
        return (int) (screenSize.height * percent/100);
    }

    public static int relativeWidth(double percent) {
        return (int) (screenSize.width * percent/100);
    }

    public static double absHeight(int pixels) {
        return (double) (100 * (double) (pixels)) / screenSize.height;
    }

    public static double absWidth(int pixels) {
        return (double) (100 * (double) (pixels)) / screenSize.width;
    }

    public static double heightToWidth(double percent) {
        return 100 * (((percent / 100.0) * (double) (screenSize.height)) / (double) (screenSize.width));
    }

    public static double widthToHeight(double percent) {
        return 100 * ((percent/100.0) * (double) (screenSize.width) / (double) (screenSize.height));
    }

    /**
     * @param image
     * @param frame
     * @return
     */
    public static Dimension letterboxImage(Dimension image, Dimension frame) {
        double aspectRatio = (double) image.width / (double) image.height;
        return aspectRatio >= 1 ? new Dimension(frame.width, (int) (frame.height*aspectRatio)) : new Dimension((int) (frame.width*aspectRatio), frame.height);
    }
}
