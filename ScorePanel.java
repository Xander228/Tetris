import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;

    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.SCORE_PANEL_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        setBorder(Constants.BLACK_BORDER);
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);
    }

    public void updateScore() {
            
    }
}
