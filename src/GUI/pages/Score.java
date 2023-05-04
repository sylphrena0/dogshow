package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.views.TableView;
import utilities.Parameters;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Score extends TableView implements Parameters {
    RoundedTextField familyName, familyEmail, name, breed, age, color, markings;
    ScoreInput obedience, socialization, grooming, fetch;
    ImageLoaderButton imageLoaderButton;
    Integer regID;

    public Score() {
        Controller controller = Controller.getInstance();

        JLabel recordsHeader = new JLabel("Score:");
        recordsHeader.setFont(headerFont);
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

        RoundedButton back = new RoundedButton("Back", pageColor, lightPurpleButtonColor, controller);
        back.setActionCommand("SCORE-LIST");
        RoundedButton save = new RoundedButton("Save Scores", lightPurpleButtonColor, Color.BLACK, controller);
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
    }

    public ScoreInput getObedience() {
        return obedience;
    }

    public ScoreInput getSocialization() {
        return socialization;
    }

    public ScoreInput getGrooming() {
        return grooming;
    }

    public ScoreInput getFetch() {
        return fetch;
    }

    public Integer getRegID() {
        return regID;
    }
}