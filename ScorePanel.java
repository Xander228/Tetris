import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    private JLabel scoreLabel; //Creates a new label object to display the score
    private JLabel lineLabel; //Creates a new label object to display the lines cleared
    private JLabel levelLabel; //Creates a new label object to display the current level

    public ScorePanel() {
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, Constants.SCORE_PANEL_HEIGHT)); //Sets the size of the board panel
        setBackground(Constants.BACKGROUND_COLOR); //Sets the background color of the panel
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR)); //Add a border around the frame
        setLayout(new BorderLayout(0, 0)); //Sets the edge offset of member panels to properly space them

        //Create a subclass of Jlabel made to display the scores
        class ScoreLabel extends JLabel {
            ScoreLabel(){
                super("", JLabel.CENTER); //Call the parent class's constructor
                this.setFont(new Font("Arial", Font.BOLD, 16)); //Sets the font and size of the label
                this.setForeground(Constants.PRIMARY_COLOR); //Sets the color of the text
            }
        }
        scoreLabel = new ScoreLabel(); //Create the ScoreLabel object that will be used to display the score
        lineLabel = new ScoreLabel(); //Create the ScoreLabel object that will be used to display the number of lines cleared
        levelLabel = new ScoreLabel(); //Create the ScoreLabel object that will be used to display the current level

        add(scoreLabel, BorderLayout.NORTH); //Adds the scoreLabel object to the top of the parent panel
        add(lineLabel, BorderLayout.CENTER); //Adds the lineLabel object to the center of the parent panel
        add(levelLabel, BorderLayout.SOUTH); //Adds the levelLabel object to the bottom of the parent panel

        this.update(0,0,1); //Initializes the panel with the starting score values
    }

    //Updates the scores
    public void update(int score, int lines, int level) {
        scoreLabel.setText("Score: " + score);
        lineLabel.setText("Lines: " + lines);
        levelLabel.setText("Level: " + level);
    }
    
}
