import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PiecePanel extends JPanel {
    private ScorePanel scorePanel; //Declare the scorePanel to store the panel that displays score, lines, and level
    private HoldPanel holdPanel; //Declare the holdPanel to store the panel that stores and displays the currently held piece
    private QueuePanel queuePanel; //Declare the queuePanel to store the panel that stores and displays the pieces currently in queue


    public PiecePanel() {
        setPreferredSize(new Dimension(Constants.PIECE_PANEL_WIDTH, Constants.BOARD_HEIGHT)); //Sets the size of the board panel
        setLayout(new BorderLayout(0, 0)); //Sets the edge offset of member panels to properly space them

        scorePanel = new ScorePanel(); //Create the ScorePanel object
        holdPanel = new HoldPanel(); //Create the HoldPanel object
        queuePanel = new QueuePanel(); //Create the QueuePanel object

        add(scorePanel, BorderLayout.NORTH); //Adds the scorePanel object to the top of the parent panel
        add(holdPanel, BorderLayout.CENTER); //Adds the holdPanel object to the center of the parent panel
        add(queuePanel, BorderLayout.SOUTH); //Adds the queuePanel object to the bottom of the parent panel
    }

    //Switches the passed in piece with the piece stored in hold panel
    public Tetromino switchPiece(Tetromino newPiece) {
        return holdPanel.switchPiece(newPiece);
    }

    //Pulls a new piece from the queue
    public Tetromino getNewPiece() {
        return queuePanel.pullFromQueue();
    }

    //Updates the scores in the score panel
    public void updateScores(int score, int lines, int level) {
        scorePanel.update(score, lines, level);
    }
}