package gui.pages;

import gui.Controller;
import gui.components.ImageLoaderButton;
import gui.components.RoundedButton;
import gui.components.RoundedTextField;
import gui.components.ScoreInput;
import gui.pages.views.TableView;
import lombok.Getter;
import utilities.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Score extends TableView {
    RoundedTextField familyName, familyEmail, name, breed, age, color, markings;
    @Getter ScoreInput obedience, socialization, grooming, fetch;
    ImageLoaderButton imageLoaderButton;
    @Getter Integer regID;

    public Score() {
        Controller controller = Controller.getInstance();

        JLabel recordsHeader = new JLabel("Score:");
        recordsHeader.setFont(Constants.headerFont);
        recordsHeader.setForeground(Color.WHITE);

        familyName = new RoundedTextField("Family Name", false, controller);
        familyEmail = new RoundedTextField("Family Email", false, controller);
        name = new RoundedTextField("Dog Name", false, controller);
        breed = new RoundedTextField("Breed", false, controller);
        age = new RoundedTextField("Age", false, controller);
        color = new RoundedTextField("Color", false, controller);
        markings = new RoundedTextField("Identifiable Markings", false, controller);

        obedience = new ScoreInput("Obedience Score", controller);
        socialization = new ScoreInput("Socialization Score", controller);
        grooming = new ScoreInput("Grooming Score", controller);
        fetch = new ScoreInput("Fetch Score", controller);
        RoundedButton back = new RoundedButton("Back", Constants.pageColor, Constants.lightPurpleButtonColor, controller);
        back.setActionCommand("SCORE-LIST");
        RoundedButton save = new RoundedButton("Save Scores", Constants.lightPurpleButtonColor, Color.BLACK, controller);
        save.setActionCommand("SCORE-SAVE");

        imageLoaderButton = new ImageLoaderButton(controller);

        addComponents(recordsHeader,
                familyName, markings,
                familyEmail, obedience,
                name, socialization,
                breed, grooming,
                age, color, fetch,
                back, save,
                imageLoaderButton);
    }

    public void setData(Object[] data) {
        contentPanel.setLayout(null); //reset layout to prevent wierd resizing issue

        regID = (Integer) data[0];
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
            throw new RuntimeException("Error recovering image from database: " + e);
        }

        contentPanel.setLayout(tableLayout); //set to use miglayout again after setText()
    }
}