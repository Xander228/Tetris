import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel;
    private JLabel lineLabel;
    private JLabel levelLabel;

    public ScorePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.SCORE_PANEL_HEIGHT));
        setBackground(Constants.BACKGROUND_COLOR);
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR));
        setLayout(new BorderLayout(0, 0));


        class ScoreLabel extends JLabel {
            ScoreLabel(){
                super("", JLabel.CENTER);
                this.setFont(new Font("Arial", Font.BOLD, 16));
                this.setForeground(Constants.PRIMARY_COLOR);
            }
        }
        scoreLabel = new ScoreLabel();
        lineLabel = new ScoreLabel();
        levelLabel = new ScoreLabel();

        add(scoreLabel, BorderLayout.NORTH);
        add(lineLabel, BorderLayout.CENTER);
        add(levelLabel, BorderLayout.SOUTH);

        this.update(0,0,1);
    }
    
    public void update(int score, int lines, int level) {
        scoreLabel.setText("Score: " + score);
        lineLabel.setText("Lines: " + lines);
        levelLabel.setText("Level: " + level);
    }
    
}
