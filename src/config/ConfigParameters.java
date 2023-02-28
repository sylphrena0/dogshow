package config;

import java.awt.*;

public interface ConfigParameters {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension pageSize = new Dimension(screenSize.width, screenSize.height - 120);
    Color backgroundColor = new Color(0x212121);
    Color headerColor = new Color(0x303030);
    Color inputColor = new Color(0x434343);
    Font inputFont = new Font("Arial", Font.PLAIN, 24);
    String separator = System.getProperty("file.separator");
    String path = System.getProperty("user.dir") + separator;
    Font headerFont = new Font("Caveat", Font.BOLD, 75);
}
