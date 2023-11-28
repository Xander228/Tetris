import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    private int i = 0;
    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        setBorder(Constants.BLACK_BORDER);
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);
    }

    public void updateScore() {
        i++;
        scoreLabel.setText("Score: " + i);
    }
}
