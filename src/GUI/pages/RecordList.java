package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedButton;
import GUI.components.RoundedCheckbox;
import GUI.pages.views.ListView;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;

public class RecordList extends ListView {
    public RecordList(Object[][] data) {
        Controller controller = Controller.getInstance();

        JLabel recordsListHeader = new JLabel("View Contestant Records:");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedCheckbox baltoEligible = new RoundedCheckbox("Balto Eligible", controller);
        RoundedButton commit = new RoundedButton("Commit Scores", lightPurpleButtonColor, Color.BLACK, controller);

        ImageIcon inspect = new ImageIcon((new ImageIcon("images/inspect.png")).getImage().getScaledInstance((int) (Scaling.relativeHeight(6.5)*0.5), (int) (Scaling.relativeHeight(6.5)*0.5),  java.awt.Image.SCALE_SMOOTH));  // use scaled icon


        addComponents(recordsListHeader, null, baltoEligible, commit);
    }
}
