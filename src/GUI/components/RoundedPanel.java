package GUI.components;

import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

// Author: b4rc0ll0
// Source: https://www.codeproject.com/Articles/114959/Rounded-Border-JPanel-JPanel-graphics-improvements

public class RoundedPanel extends JPanel implements ConfigParameters {  //stroke size. it is recommended to set it to 1 for better view
    protected int strokeSize = 1; //sets if it drops shadow
    protected Color shadowColor = Color.black; //color of shadow
    protected boolean shady = true; //sets if it has a High Quality view
    protected boolean highQuality = true; //double values for Horizontal and Vertical radius of corner arcs
    protected Dimension arcs = new Dimension(Scaling.relativeHeight(12), Scaling.relativeHeight(12)); //distance between shadow border and opaque panel border
    protected int shadowGap = 5; //the offset of shadow.
    protected int shadowOffset = 4; //the transparency value of shadow. ( 0 - 255)
    protected int shadowAlpha = 0;
    private Color color;
    public RoundedPanel() {
        this.setOpaque(false);
        this.color = pageColor;
    }

    public RoundedPanel(int arc, Color background) {
        this();
        this.arcs = new Dimension(arc, arc);
        this.color = background;
        this.setBorder(componentInsets);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColorA = new Color(shadowColor.getRed(),
                shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
        Graphics2D graphics = (Graphics2D) g;

        //sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //draws shadow borders if any.
        if (shady) {
            graphics.setColor(shadowColorA);
            graphics.fillRoundRect(
                    shadowOffset, //x position
                    shadowOffset, //y position
                    width - strokeSize - shadowOffset, //width
                    height - strokeSize - shadowOffset, //height
                    arcs.width, arcs.height); //arc Dimension
        } else {
            shadowGap = 1;
        }

        //draws the rounded opaque panel with borders.
        graphics.setColor(color);
        graphics.fillRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(strokeSize));
        graphics.drawRoundRect(0, 0, width - shadowGap,
                height - shadowGap, arcs.width, arcs.height);

        //sets strokes to default, is better.
        graphics.setStroke(new BasicStroke());
    }
}
