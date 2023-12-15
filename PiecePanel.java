import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PiecePanel extends JPanel {
    private HoldPanel holdPanel;
    private QueuePanel queuePanel;

    public PiecePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        setLayout(new BorderLayout(0, 0));
        holdPanel = new HoldPanel();
        queuePanel = new QueuePanel();
        add(holdPanel,BorderLayout.NORTH);
        add(queuePanel,BorderLayout.CENTER);
    }

    public void updateScore() {
            
    }
}
