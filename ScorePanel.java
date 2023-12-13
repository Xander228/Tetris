import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    private int i = 0;
    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.FRAME_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        setBackground(Constants.COLORS[0][0]);
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);
    }

    public void updateScore() {
        i++;
        scoreLabel.setText("Score: " + i);
    }
}
