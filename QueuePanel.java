import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QueuePanel extends JPanel {
    private JLabel pieceLabel;

    public QueuePanel() {
        // Initialize components, set layout, etc.
        setBackground(Constants.COLORS[0][0]);
        pieceLabel = new JLabel("QUEUE");
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(pieceLabel);
    }

    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Draw.header(g);

    }
    
    public void updateScore() {
            
    }
}
