import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    int i = 0;
    int key = 0;
    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.FRAME_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        //setBackground(Constants.COLORS[0][0]);
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);
    }
    
    public void handleKeyPress(int key){
        this.key = key;
    }
    
    public void updateScore() {
        i++;
        scoreLabel.setText("Score: " + i);
        //scoreLabel.setText("key: " + key);
    }
    
}
