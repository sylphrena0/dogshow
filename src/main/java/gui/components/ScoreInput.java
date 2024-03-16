package gui.components;

import gui.Controller;
import net.miginfocom.swing.MigLayout;
import utilities.Constants;

import javax.swing.*;
import java.awt.*;

public class ScoreInput extends RoundedPanel {

    private final RoundedTextField score1, score2, score3, score4;
    private final JLabel scoreLabel;

    public ScoreInput(String label, Controller controller) {
        super(15, Constants.inputColor);
        this.setLayout(new MigLayout(
                "fill",
                "[fill][fill, sg][fill, sg][fill, sg][fill, sg]", // Column constraints (fill makes components grow to row size, sg constrains each row/column to be the same size)
                "[fill]" // Row constraints
        ));

        scoreLabel = new JLabel(label);
        scoreLabel.setFont(Constants.inputFont);
        scoreLabel.setForeground(Color.WHITE);

        score1 = new RoundedTextField("-", controller, Constants.pageColor);
        score2 = new RoundedTextField("-", controller, Constants.pageColor);
        score3 = new RoundedTextField("-", controller, Constants.pageColor);
        score4 = new RoundedTextField("-", controller, Constants.pageColor);

        this.add(scoreLabel, "w 32%!");
        this.add(score1, "w 14%!");
        this.add(score2, "w 14%!");
        this.add(score3, "w 14%!");
        this.add(score4, "w 14%!");
    }

    public ScoreInput(String label, Boolean enabled, Controller controller) {
        this(label, controller);
        if (Boolean.FALSE.equals(enabled)) {
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
            scoreLabel.setForeground(Constants.disabledText);
            score1.setEnabled(false);
            score2.setEnabled(false);
            score3.setEnabled(false);
            score4.setEnabled(false);
        }
    }

    @Override
    public boolean isEnabled() {
        return score1.isEnabled();
    }

    public void setScores(String[] scores) {
        score1.setText(scores[0]);
        score2.setText(scores[1]);
        score3.setText(scores[2]);
        score4.setText(scores[3]);
    }

    public String getScores() {
        return score1.getText() + ";" + score2.getText() + ";" + score3.getText() + ";" + score4.getText();
    }

}
