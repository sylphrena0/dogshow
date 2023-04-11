package GUI.components;

import GUI.Controller;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ScoreInput extends RoundedPanel {

    private RoundedTextField score1, score2, score3, score4;
    private JLabel scoreLabel;

    public ScoreInput(String label, Controller controller) {
        super(15, inputColor);
        this.setLayout(new MigLayout(
                "fill",
                "[fill][fill, sg][fill, sg][fill, sg][fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill]" // Row constraints
        ));

        scoreLabel = new JLabel(label);
        scoreLabel.setFont(inputFont);
        scoreLabel.setForeground(Color.WHITE);

        score1 = new RoundedTextField("-", controller, pageColor);
        score2 = new RoundedTextField("-", controller, pageColor);
        score3 = new RoundedTextField("-", controller, pageColor);
        score4 = new RoundedTextField("-", controller, pageColor);

        this.add(scoreLabel, "w 30%");
        this.add(score1);
        this.add(score2);
        this.add(score3);
        this.add(score4);
    }

    public ScoreInput(String label, Boolean enabled, Controller controller) {
        this(label, controller);
        if (!enabled) {
            this.setEnabled(false);
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled) {
            scoreLabel.setForeground(Color.WHITE);
            score1.setEnabled(true);
            score2.setEnabled(true);
            score3.setEnabled(true);
            score4.setEnabled(true);
        } else {
            scoreLabel.setForeground(disabledText);
            score1.setEnabled(false);
            score2.setEnabled(false);
            score3.setEnabled(false);
            score4.setEnabled(false);
        }
    }

}
