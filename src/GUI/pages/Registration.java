package GUI.pages;

import GUI.Controller;
import GUI.components.*;
import GUI.pages.layouts.TableLayout;
import utilities.Scaling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Registration extends TableLayout {
    private final ImageLoaderButton imageLoaderButton;
    public Registration() {
        Controller controller = Controller.getInstance();

        JLabel registrationHeader = new JLabel("Register a contestant:");
        registrationHeader.setFont(headerFont);
        registrationHeader.setForeground(Color.WHITE);
        registrationHeader.setHorizontalAlignment(SwingConstants.LEFT);

        RoundedTextField familyName = new RoundedTextField("Family Name", controller);
        RoundedTextField familyEmail = new RoundedTextField("Family Email", controller);
        RoundedTextField name = new RoundedTextField("Dog Name", controller);
        RoundedTextField breed = new RoundedTextField("Breed", controller);
        RoundedTextField age = new RoundedTextField("Age", controller);
        RoundedTextField color = new RoundedTextField("Color", controller);

        RoundedTextField markings = new RoundedTextField("Identifiable Markings", controller);
        RoundedCheckbox obedience = new RoundedCheckbox("Register for Obedience Contest", controller);
        RoundedCheckbox socialization = new RoundedCheckbox("Register for Socialization Contest", controller);
        RoundedCheckbox grooming = new RoundedCheckbox("Register for Grooming Contest", controller);
        RoundedCheckbox fetch = new RoundedCheckbox("Register for Play Fetch Contest", controller);

        imageLoaderButton = new ImageLoaderButton(controller);
        imageLoaderButton.setActionCommand("SET_ICON");

        RoundedButton back = new RoundedButton("Back", pageColor, lightPurpleButtonColor, controller);
        RoundedButton registerButton = new RoundedButton("Register", lightPurpleButtonColor, Color.BLACK, controller);


        JPanel buttons = new JPanel(new GridLayout(1,2, gridPaddingRegistration, gridPaddingRegistration));
        buttons.add(registerButton);

        addComponents(registrationHeader, null, null,
                        familyName, markings,
                        familyEmail, obedience,
                        name, socialization,
                        breed, grooming,
                        age, color, fetch,
                        back, registerButton,
                        imageLoaderButton);
    }

    public void setDogImage(String file) {
        try {
            //scaling solution from https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File(file))); // load the image to a imageIcon
            System.out.println(new Dimension(imagePanel.getWidth(), imagePanel.getHeight()));
            Dimension letterboxed = Scaling.letterboxImage(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()), new Dimension(imagePanel.getWidth(), imagePanel.getHeight())); // group2width and group1height are protected ints in TableLayout.java
            System.out.println(letterboxed);
            Image scaledImage = imageIcon.getImage().getScaledInstance(letterboxed.width, letterboxed.height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageLoaderButton.setImage(scaledImage);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }


    }
}
