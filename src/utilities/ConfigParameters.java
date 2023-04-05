package utilities;

import GUI.Controller;

import java.awt.*;

public interface ConfigParameters {
    Dimension screenSize = Controller.screenSize();
    Dimension pageSize = new Dimension(screenSize.width, screenSize.height - Scaling.relativeHeight(6.5));
    Color greenButtonColor = new Color(0x93c47d);
    Color purpleButtonColor = new Color(0x7c50e2);
    Color backgroundColor = new Color(0x212121);
    Color pageColor = new Color(0x303030);
    Color inputColor = new Color(0x434343);
    Color transparent = new Color(0, 0, 0, 0);
    Color disabledText = new Color(0x999999);
    Font inputFont = new Font("Arial", Font.PLAIN, Scaling.relativeHeight(1.5));
    Font headerFont = CaveatFont.getFont(Font.BOLD, Scaling.relativeHeight(4.2)); //used font that is included with package
    Font buttonFont = CaveatFont.getFont(Font.BOLD, Scaling.relativeHeight(2));
    int gridPadding = Scaling.relativeHeight(2.2);
    int pagePadding = Scaling.relativeHeight(4.5);
}
