import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    private JLabel gamePanel;
    
    public TetrisBoard() {
        // Initialize components, set layout, etc.
        gamePanel = new JLabel("Score: 0");
        add(gamePanel);
        
    }
    
    public void update() {
            
    }
    
    public void handleKeyPress(int key){
        
    }
}
