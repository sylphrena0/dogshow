package utilities;

import gui.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public interface Parameters {
    String PATH_SEPARATOR = System.getProperty("file.separator");
    Dimension screenSize = Controller.screenSize();
    Dimension pageSize = new Dimension(screenSize.width, screenSize.height - Utilities.relativeHeight(6.5));
    Color lightPurpleButtonColor = new Color(0xBB86FC);
    Color errorRed = new Color(0xFA4364);
    Color backgroundColor = new Color(0x212121);
    Color pageColor = new Color(0x303030);
    Color inputColor = new Color(0x434343);
    Color transparent = new Color(0, 0, 0, 0);
    Color disabledText = new Color(0x999999);
    Border componentInsets = BorderFactory.createEmptyBorder(Utilities.relativeHeight(0.576),Utilities.relativeHeight(1.728),Utilities.relativeHeight(0.576),Utilities.relativeHeight(1.728));
    Font inputFont = new Font("Arial", Font.PLAIN, Utilities.relativeHeight(1.5));
    Font headerFont = Utilities.getCaveatFont(Font.BOLD, Utilities.relativeHeight(4.2)); //used font that is included with package
    Font buttonFont = Utilities.getCaveatFont(Font.BOLD, Utilities.relativeHeight(2));
    int GRID_PADDING = Utilities.relativeHeight(2.2);
    int PAGE_PADDING = Utilities.relativeHeight(4.5);
}
