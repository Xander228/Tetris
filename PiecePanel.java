import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PiecePanel extends JPanel {
    private JLabel pieceLabel;

    public PiecePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        setBorder(Constants.BLACK_BORDER);
        pieceLabel = new JLabel("Piece");
        add(pieceLabel);
    }

    public void updateScore() {
            
    }
}
