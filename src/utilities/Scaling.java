package utilities;

import java.awt.*;

public class Scaling implements ConfigParameters {
    public static int relativeHeight(double percent) {
        return (int) (screenSize.height * percent/100);
    }

    public static int relativeWidth(double percent) {
        return (int) (screenSize.width * percent/100);
    }

}
