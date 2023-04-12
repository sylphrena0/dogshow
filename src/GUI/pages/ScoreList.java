package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.layouts.ListLayout;
import utilities.ConfigParameters;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScoreList extends ListLayout implements ConfigParameters {
    public ScoreList() {
        Controller controller = Controller.getInstance();

        JLabel recordsListHeader = new JLabel("Score: Balto");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedCheckbox baltoEligible = new RoundedCheckbox("Balto Eligible", controller);
        RoundedButton commit = new RoundedButton("Commit Scores", lightPurpleButtonColor, Color.BLACK, controller);


        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};

        //Its data is initialized and stored in a two-dimensional Object array:

        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", 5, false},
                {"John", "Doe",
                        "Rowing", 3, true},
                {"Sue", "Black",
                        "Knitting", 2, false},
                {"Jane", "White",
                        "Speed reading", 20, true},
                {"Joe", "Brown",
                        "Pool", 10, false},
                {"Kathy", "Smith",
                        "Snowboarding", 5, false},
                {"John", "Doe",
                        "Rowing", 3, true},
                {"Sue", "Black",
                        "Knitting", 2, false},
                {"Jane", "White",
                        "Speed reading", 20, true},
                {"Joe", "Brown",
                        "Pool", 10, false},
                {"Krish", "Smith",
                        "Snowboarding", 5, false},
                {"Snow", "Doe",
                        "Rowing", 3, true},
                {"Stew", "Black",
                        "Knitting", 2, false},
                {"Drwaft", "White",
                        "Speed reading", 20, true},
                {"Matt", "Brown",
                        "Pool", 10, false}
        };


        addComponents(recordsListHeader, null, baltoEligible, commit, columnNames, data);
    }
}
