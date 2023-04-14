package GUI.pages;

import GUI.Controller;
import GUI.components.RoundedButton;
import GUI.components.RoundedCheckbox;
import GUI.pages.layouts.ListLayout;

import javax.swing.*;
import java.awt.*;

public class RecordList extends ListLayout  {
    public RecordList() {
        Controller controller = Controller.getInstance();

        JLabel recordsListHeader = new JLabel("View Contestant Records:");
        recordsListHeader.setFont(headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedCheckbox baltoEligible = new RoundedCheckbox("Balto Eligible", controller);
        RoundedButton commit = new RoundedButton("Commit Scores", lightPurpleButtonColor, Color.BLACK, controller);


        String[] columnNames = {"REG ID",
                "Name",
                "Obedience",
                "Socialization",
                "Grooming",
                "Fetch",
                "Balto Winner",
                ""};

        //Its data is initialized and stored in a two-dimensional Object array:

        Object[][] data = {
                {"5121", "Balto", 10, 8, 2, 7, true,""},
                {"9231", "Fideo", 6, 3, 5, 1, true,""},
                {"9823", "Black", 6, 1, 3, null, false,""},
                {"2934", "Percy", 10, 10, 10, 10, true,""},
                {"2214", "Cerberus", 5, 1, 2, 3, true,""},
                {"1213", "Precious", 6, 1, 3, null, true,""},
                {"5121", "Balto", 10, 8, 2, 7, true,""},
                {"9231", "Fideo", 6, 3, 5, 1, true,""},
                {"9823", "Black", 6, 1, 3, null, false,""},
                {"2934", "Percy", 10, 10, 10, 10, true,""},
                {"2214", "Cerberus", 5, 1, 2, 3, true,""},
                {"1213", "Precious", 6, 1, 3, null, true,""}
        };


        addComponents(recordsListHeader, null, baltoEligible, commit, columnNames, data);
    }
}
