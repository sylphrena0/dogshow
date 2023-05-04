package utilities;

import GUI.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface ConfigParameters {
    Dimension screenSize = Controller.screenSize();
    Dimension pageSize = new Dimension(screenSize.width, screenSize.height - Scaling.relativeHeight(6.5));
    Color lightPurpleButtonColor = new Color(0xBB86FC);
    Color errorRed = new Color(0xFA4364);
    Color backgroundColor = new Color(0x212121);
    Color pageColor = new Color(0x303030);
    Color inputColor = new Color(0x434343);
    Color transparent = new Color(0, 0, 0, 0);
    Color disabledText = new Color(0x999999);
    Border componentInsets = BorderFactory.createEmptyBorder(Scaling.relativeHeight(0.576),Scaling.relativeHeight(1.728),Scaling.relativeHeight(0.576),Scaling.relativeHeight(1.728));
    Font inputFont = new Font("Arial", Font.PLAIN, Scaling.relativeHeight(1.5));
    Font headerFont = CaveatFont.getFont(Font.BOLD, Scaling.relativeHeight(4.2)); //used font that is included with package
    Font buttonFont = CaveatFont.getFont(Font.BOLD, Scaling.relativeHeight(2));
    int gridPadding = Scaling.relativeHeight(2.2);
    int pagePadding = Scaling.relativeHeight(4.5);
}
