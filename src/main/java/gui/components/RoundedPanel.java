package gui.components;

import utilities.Constants;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;

// Author: b4rc0ll0
// Source: https://www.codeproject.com/Articles/114959/Rounded-Border-JPanel-JPanel-graphics-improvements

public class RoundedPanel extends JPanel {  // stroke size. it is recommended to set it to 1 for better view
    protected int strokeSize = 1; // sets if it drops shadow
    protected boolean highQuality = true; // sets if it has a High Quality view
    protected Dimension arcs = new Dimension(Utilities.relativeHeight(12), Utilities.relativeHeight(12)); // double values for Horizontal and Vertical radius of corner arcs
    private Color color;
    public RoundedPanel() {
        this.setOpaque(false);
        this.color = Constants.pageColor;
    }

    public RoundedPanel(int arc, Color background) {
        this();
        this.arcs = new Dimension(arc, arc);
        this.color = background;
        this.setBorder(Constants.componentInsets);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = 1;
        Graphics2D graphics = (Graphics2D) g;

        // sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        // draws the rounded opaque panel with borders.
        graphics.setColor(color);
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        // sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
}
