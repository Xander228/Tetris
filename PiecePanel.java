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
        setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, Constants.ACCENT_COLOR));
        setBackground(Constants.COLORS[0][0]);
        holdPanel = new HoldPanel();
        queuePanel = new QueuePanel();
        add(holdPanel);
        add(queuePanel);
    }

    public void updateScore() {
            
    }
}
