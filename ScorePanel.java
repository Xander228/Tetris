import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    int i = 0;
    int key = 0;
    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        //setBackground(Constants.COLORS[0][0]);
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);
    }
    
    public void update() {
        i++;
        scoreLabel.setText("Score: " + i + "\n helwo uwu");
        //scoreLabel.setText("key: " + key);
    }
    
}
