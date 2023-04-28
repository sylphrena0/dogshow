package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.views.ListView;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.swing.*;
import java.awt.*;



public class ScoreList extends ListView implements ConfigParameters {
    public ScoreList(Object[][] data) {
        Controller controller = Controller.getInstance();

        JLabel recordsListHeader = new JLabel("Scores");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedCheckbox baltoEligible = new RoundedCheckbox("Balto Eligible", controller);
        RoundedButton commit = new RoundedButton("Commit Scores", lightPurpleButtonColor, Color.BLACK, controller);
        baltoEligible.setActionCommand("BALTO");
        baltoEligible.addActionListener(controller);


        //Its data is initialized and stored in a two-dimensional Object array:

        addComponents(recordsListHeader, null, baltoEligible, commit);
    }

    public  String getTable() {
        return table.toString();
    }

}
