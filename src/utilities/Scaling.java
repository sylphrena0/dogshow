package utilities;

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

}
