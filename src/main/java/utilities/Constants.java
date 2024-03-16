package utilities;

import gui.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.nio.file.FileSystems;

public final class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String PATH_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final Dimension screenSize = Controller.screenSize();
    public static final Dimension pageSize = new Dimension(screenSize.width, screenSize.height - Utilities.relativeHeight(6.5));
    public static final Color lightPurpleButtonColor = new Color(0xBB86FC);
    public static final Color errorRed = new Color(0xFA4364);
    public static final Color backgroundColor = new Color(0x212121);
    public static final Color pageColor = new Color(0x303030);
    public static final Color inputColor = new Color(0x434343);
    public static final Color transparent = new Color(0, 0, 0, 0);
    public static final Color disabledText = new Color(0x999999);
    public static final Border componentInsets = BorderFactory.createEmptyBorder(Utilities.relativeHeight(0.576),Utilities.relativeHeight(1.728),Utilities.relativeHeight(0.576),Utilities.relativeHeight(1.728));
    public static final Font inputFont = new Font("Arial", Font.PLAIN, Utilities.relativeHeight(1.5));
    public static final Font headerFont = Utilities.getCaveatFont(Font.BOLD, Utilities.relativeHeight(4.2)); //used font that is included with package
    public static final Font buttonFont = Utilities.getCaveatFont(Font.BOLD, Utilities.relativeHeight(2));
    public static final int GRID_PADDING = Utilities.relativeHeight(2.2);
    public static final int PAGE_PADDING = Utilities.relativeHeight(4.5);
}
