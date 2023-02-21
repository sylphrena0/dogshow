package config;

import java.awt.*;

public interface ConfigParameters {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static  Dimension pageSize = new Dimension(screenSize.width, screenSize.height - 120);
    public static Color backgroundColor = new Color(0x212121);
    public static Color headerColor = new Color(0x303030);

}