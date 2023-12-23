import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QueuePanel extends JPanel {
    private JLabel pieceLabel;
    Tetromino tetromino;
    
    public QueuePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.QUEUE_PANEL_HEIGHT));
        setBackground(Constants.COLORS[0][0]);
        pieceLabel = new JLabel("QUEUE");
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(pieceLabel);
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Draw.header(g);
        for(int i = 0; i < 3; i++) {
            tetromino = new Tetromino((TetrisFrame.i + i) % 7, Constants.PIECE_PANEL_WIDTH / 2, (int)(Constants.HOLD_PANEL_HEIGHT * 0.55) + i * 4 * Constants.PIECE_SIZE);
            tetromino.draw(g);
        }
        
    }
    
    public void update() {
        repaint();
    }
}
