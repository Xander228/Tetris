import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    int key = 0;
    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.FRAME_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, Constants.ACCENT_COLOR));
        //setBackground(Constants.COLORS[0][0]);
        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);
    }
    
    public void handleKeyPress(int key){
        this.key = key;
    }
    
    public void updateScore() {
        scoreLabel.setText("key: " + key);
    }
    
}
