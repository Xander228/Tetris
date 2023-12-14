import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QueuePanel extends JPanel {
    private JLabel pieceLabel;

    public QueuePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.BOARD_HEIGHT / 2));
        setBackground(Constants.COLORS[0][0]);
        pieceLabel = new JLabel("Piece");
        add(pieceLabel);
    }

    public void updateScore() {
            
    }
}
