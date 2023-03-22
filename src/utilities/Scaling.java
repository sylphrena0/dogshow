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

    private int[] letterboxImage(int imageWidth, int imageHeight, int frameWidth, int frameHeight) {
        double scaleFactor = 1.0;

        // Scale by the longer edge
        if (frameWidth > frameHeight) {
            scaleFactor = imageWidth/(double)frameWidth;
        } else {
            scaleFactor = imageHeight/(double)frameHeight;
        }

        int sx1 = 0;
        int sy1 = 0;
        int sx2 = frameWidth;
        int sy2 = frameHeight;

        int dx1 = (imageWidth-(int)(frameWidth*scaleFactor))/2;
        int dy1 = (imageHeight-(int)(frameHeight*scaleFactor))/2;
        int dx2 = (imageWidth+(int)(frameWidth*scaleFactor))/2;
        int dy2 = (imageHeight+(int)(frameHeight*scaleFactor))/2;


        gc.fillRect(0, 0, imageWidth, imageHeight);
        gc.drawImage(srcImage, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
        gc.dispose();
        return new int[] {2, 2};
    }

}
