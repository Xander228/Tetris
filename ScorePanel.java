import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel;
    private JLabel lineLabel;
    private JLabel levelLabel;

    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        setBackground(Constants.BACKGROND_COLOR);
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROND_COLOR));
        setLayout(new BorderLayout(0, 0));

        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        lineLabel = new JLabel("Lines: 0", JLabel.CENTER);
        levelLabel = new JLabel("Level: 1", JLabel.CENTER);

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lineLabel.setFont(new Font("Arial", Font.BOLD, 16));
        levelLabel.setFont(new Font("Arial", Font.BOLD, 16));

        scoreLabel.setForeground(Constants.ACCENT_COLOR);
        lineLabel.setForeground(Constants.ACCENT_COLOR);
        levelLabel.setForeground(Constants.ACCENT_COLOR);

        add(scoreLabel, BorderLayout.NORTH);
        add(lineLabel, BorderLayout.CENTER);
        add(levelLabel, BorderLayout.SOUTH);
    }
    
    public void update(int score, int lines, int level) {
        scoreLabel.setText("Score: " + score);
        lineLabel.setText("Lines: " + lines);
        levelLabel.setText("Level: " + level);
    }
    
}
