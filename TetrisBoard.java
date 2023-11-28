import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    private JLabel boardLabel;
    public TetrisBoard() {
        // Initialize components, set layout
        boardLabel = new JLabel("Board");
        setBackground(Color.GRAY);
        add(boardLabel);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 

    }
    
    public void update() {
    }
    
    public void drawBoard(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(30, 0, 300, 300);
    }
    
    public void drawGrid(Graphics g) {
        for(int i = 0; i < Constants.BOARD_COLS / 2; i++) {
            g.fillRect(30, 0, 300, 300);
        }
    }
    
    public void handleKeyPress(int key){
        
    }
}
