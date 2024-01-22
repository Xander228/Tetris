import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PiecePanel extends JPanel {
    private ScorePanel scorePanel;
    private HoldPanel holdPanel;
    private QueuePanel queuePanel;

    public PiecePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        setLayout(new BorderLayout(0, 0));
        
        scorePanel = new ScorePanel();
        holdPanel = new HoldPanel();
        queuePanel = new QueuePanel();
        
        add(scorePanel,BorderLayout.NORTH);
        add(holdPanel,BorderLayout.CENTER);
        add(queuePanel,BorderLayout.SOUTH);
    }

    public Tetromino switchPiece(Tetromino newPiece) {
        return holdPanel.switchPiece(newPiece);
    }

    public Tetromino getNewPiece() {
        return queuePanel.pullFromQueue();
    }
    public void update() {
        scorePanel.update();
        holdPanel.update();
        queuePanel.update();
    }
}
