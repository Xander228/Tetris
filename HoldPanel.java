import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HoldPanel extends JPanel {
    private JLabel pieceLabel;
    Tetromino tetromino;
    
    public HoldPanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.HOLD_PANEL_HEIGHT));
        setBackground(Constants.COLORS[0][0]);
        pieceLabel = new JLabel("HOLD");
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(pieceLabel);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Draw.header(g);
        tetromino = new Tetromino(0, Constants.PIECE_PANEL_WIDTH / 2, (int)(Constants.HOLD_PANEL_HEIGHT * 0.55), false);
        tetromino.draw(g);
    }
    
    public void update() {
        repaint();
    }
}
