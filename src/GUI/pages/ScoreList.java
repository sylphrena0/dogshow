package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.views.ListView;
import utilities.ConfigParameters;

import javax.swing.*;
import java.awt.*;



public class ScoreList extends ListView implements ConfigParameters {
    public ScoreList() {
        Controller controller = Controller.getInstance();

        JLabel recordsListHeader = new JLabel("Scores");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedCheckbox baltoEligible = new RoundedCheckbox("Balto Eligible", controller);
        RoundedButton commit = new RoundedButton("Commit Scores", lightPurpleButtonColor, Color.BLACK, controller);
        baltoEligible.setActionCommand("BALTO");
        baltoEligible.addActionListener(controller);


        String[] columnNames = {"REG ID",
                "Name",
                "Obedience",
                "Socialization",
                "Grooming",
                "Fetch",
                "Balto Eligible"};

        //Its data is initialized and stored in a two-dimensional Object array:

        Object[][] data = {
                {4512, "Balto", 10, 8, 2, 7, true},
                {9231, "Fideo", 6, 3, 5, 1, true},
                {1824, "Percy", 6, 1, 3, "-", false},
                {3491, "Cerberus", 10, 10, 10, 10, true},
                {1923, "Precious", 5, 1, 2, 3, true},
                {5383, "Kohl", "-", 3, 4, 1, false},
                {3481, "Roast", 8, 7, "-", 3, false},
                {2812, "Mouse", "-", "-", 7, "-", false},
                {2319, "Boo", 2, 3, 1, 9, true},
                {7912, "Bella", 6, 8, 5, 7, true},
                {4554, "Max", 9, 5, 4, 9, true},
                {2808, "Charlie", 9, "-", 5, 6, false},
                {6429, "Benito", 10, 8, 9, 7, true},
                {6776, "Daisy", 4, 6, 8, 6, true},
                {1198, "Milo", 3, 9, "-", 8, false},
                {8175, "Cooper", 7, 8, 9, 5, true}
        };

        addComponents(recordsListHeader, null, baltoEligible, commit, columnNames, data);


    }
}
