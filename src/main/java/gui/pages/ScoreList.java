package gui.pages;

import gui.Controller;
import gui.components.RoundedButton;
import gui.pages.views.ListView;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;



public class ScoreList extends ListView {
    public ScoreList() {
        Controller controller = Controller.getInstance();

        String[] columnNames = {"REG ID",
                "Name",
                "Obedience",
                "Socialization",
                "Grooming",
                "Fetch",
                "Balto Eligible",
                ""};

        JLabel recordsListHeader = new JLabel("Scores");
        recordsListHeader.setFont(Constants.headerFont);
        recordsListHeader.setForeground(Color.WHITE);
        recordsListHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedButton commit = new RoundedButton("Commit Scores", Constants.lightPurpleButtonColor, Color.BLACK, controller);
        commit.setActionCommand("SCORE-COMMIT");

        //Its data is initialized and stored in a two-dimensional Object array:
        addComponents(recordsListHeader, commit, columnNames);
    }

}
