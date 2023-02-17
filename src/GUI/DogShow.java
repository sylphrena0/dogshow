package GUI;

import demo_structure.ControllingFrame;

import javax.swing.*;

public class DogShow {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> Controller.getInstance("Dog Show"));
    }

}
