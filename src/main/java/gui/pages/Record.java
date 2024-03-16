package gui.pages;

import gui.Controller;
import gui.components.ImageLoaderButton;
import gui.components.RoundedButton;
import gui.components.RoundedTextField;
import gui.components.ScoreInput;
import gui.pages.views.TableView;
import utilities.Constants;
import utilities.Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Record extends TableView {
    RoundedTextField familyName, familyEmail, name, breed, age, color, markings;
    ScoreInput obedience, socialization, grooming, fetch;
    ImageLoaderButton imageLoaderButton;
    private final transient Logger logger = Logger.getLogger(getClass().getName());
    public Record() {
        Controller controller = Controller.getInstance();

        JLabel recordsHeader = new JLabel("View Records:");
        recordsHeader.setFont(Constants.headerFont);
        recordsHeader.setForeground(Color.WHITE);
        recordsHeader.setHorizontalAlignment(SwingConstants.LEFT);

        familyName = new RoundedTextField("Family Name", false, controller);
        familyEmail = new RoundedTextField("Family Email", false, controller);
        name = new RoundedTextField("Dog Name", false, controller);
        breed = new RoundedTextField("Breed", false, controller);
        age = new RoundedTextField("Age", false, controller);
        color = new RoundedTextField("Color", false, controller);
        markings = new RoundedTextField("Identifiable Markings", false, controller);

        obedience = new ScoreInput("Obedience", false, controller);
        socialization = new ScoreInput("Socialization", false, controller);
        grooming = new ScoreInput("Grooming", false, controller);
        fetch = new ScoreInput("Fetch", false, controller);

        RoundedButton back = new RoundedButton("Back", Constants.pageColor, Constants.lightPurpleButtonColor, controller);
        RoundedButton winnerBanner = new RoundedButton("2022 Balto Award Winner", Constants.lightPurpleButtonColor, Color.BLACK, controller);
        winnerBanner.setEnabled(false);

        back.setActionCommand("RECORD-LIST");

        imageLoaderButton = new ImageLoaderButton(controller);

        addComponents(recordsHeader,
                familyName, markings,
                familyEmail, obedience,
                name, socialization,
                breed, grooming,
                age, color, fetch,
                back, winnerBanner,
                imageLoaderButton);
    }

    public void setData(Object[] data) {
        contentPanel.setLayout(null); //reset layout to prevent wierd resizing issue

        familyName.setText((String) data[1]);
        familyEmail.setText((String) data[2]);
        name.setText((String) data[3]);
        breed.setText((String) data[4]);
        age.setText(((Integer) data[5]).toString());
        color.setText((String) data[6]);
        markings.setText((String) data[7]);

        if (data[8].equals("-")) {
            obedience.setEnabled(false);
        } else if (!data[8].equals(" ")) {
            obedience.setScores(((String) data[8]).split(";"));
        }

        if (data[9].equals("-")) {
            socialization.setEnabled(false);
        } else if (!data[9].equals(" ")) {
            socialization.setScores(((String) data[9]).split(";"));
        }

        if (data[10].equals("-")) {
            grooming.setEnabled(false);
        } else if (!data[10].equals(" ")) {
            grooming.setScores(((String) data[10]).split(";"));
        }

        if (data[11].equals("-")) {
            fetch.setEnabled(false);
        } else if (!data[11].equals(" ")) {
            fetch.setScores(((String) data[11]).split(";"));
        }

        try {
            imageLoaderButton.setImage(ImageIO.read(new ByteArrayInputStream((byte[]) data[12])));
        } catch (IOException e) {
            Utilities.showInternalError("Unable to recover image from database.", logger, e);
        }

        contentPanel.setLayout(tableLayout); //set to use miglayout again after setText()
    }
}
