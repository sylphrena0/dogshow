package GUI.components;

import GUI.Controller;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class ScoreInput extends RoundedPanel {
    private final Controller controller;

    public ScoreInput(String label) {
        super(15);
        this.controller = Controller.getInstance();
        this.setLayout(new MigLayout(
                "fill",
                "[fill][fill, sg][fill, sg][fill, sg][fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill]" // Row constraints
        ));
        this.add(new JLabel(label));
        this.add(new RoundedTextField("-", controller));
        this.add(new RoundedTextField("-", controller));
        this.add(new RoundedTextField("-", controller));
        this.add(new RoundedTextField("-", controller));
    }
}
