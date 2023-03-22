package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import utilities.ConfigParameters;
import utilities.Scaling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RegistrationPage extends TableLayout {
    private ImageLoaderButton imageLoaderButton;
    public RegistrationPage() {
        Controller controller = Controller.getInstance();

        JLabel registrationHeader = new JLabel("Register a contestant:");
        registrationHeader.setFont(new Font("Caveat", Font.BOLD, Scaling.relativeHeight(4.2)));
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedTextField familyName = new RoundedTextField("Family Name", controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", controller);
        RoundedTextField name = new RoundedTextField("Dog Name", controller);
        RoundedTextField breed = new RoundedTextField("Breed", controller);
        RoundedTextField age = new RoundedTextField("Age", controller);
        RoundedTextField color = new RoundedTextField("Color", controller);

        JPanel ageAndColor = new JPanel(new GridLayout(1, 2, gridPaddingRegistration, gridPaddingRegistration));
        ageAndColor.setOpaque(false);
        ageAndColor.add(age);
        ageAndColor.add(color);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", controller);
        RoundedCheckbox obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        RoundedCheckbox socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        RoundedCheckbox grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        RoundedCheckbox fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        RoundedButton registerButton = new RoundedButton("Register", greenButtonColor, Color.BLACK, controller);

        imageLoaderButton = new ImageLoaderButton(controller);
        imageLoaderButton.setActionCommand("SET_ICON");

        RoundedButton uploadButton = new RoundedButton("Upload Dog Picture", purpleButtonColor, Color.WHITE, controller);
        uploadButton.setActionCommand("SET_ICON");

        addComponents(registrationHeader,
                        familyName, markings,
                        familyEmail, obedience,
                        name, socialization,
                        breed, grooming,
                        ageAndColor, fetch,
                        registerButton,
                        imageLoaderButton,
                        uploadButton);


    }

    public void setDogImage(String file) {
        try {
            //scaling solution from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File(file))); // load the image to a imageIcon
            Dimension letterboxed = Scaling.letterboxImage(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()), new Dimension(group2width, group1height)); // group2width and group1height are protected ints in TableLayout.java
            Image scaledImage = imageIcon.getImage().getScaledInstance(letterboxed.width, letterboxed.height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageLoaderButton.setImage(new ImageIcon(scaledImage));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }


    }
}
