import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    private JLabel boardLabel;
    public TetrisBoard() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        boardLabel = new JLabel("Board");
        setBackground(new Color((int)0x21477d).brighter());
        add(boardLabel);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawGrid(g);
    }
    
    public void update() {
    }
    
    public void drawBoard(Graphics g) {

    }
    
    public void drawGrid(Graphics g) {
        g.setColor(new Color((int)0x21477d).brighter().brighter());
        for(int i = 0; i < Constants.BOARD_COLS / 2; i++) {
            g.fillRect(i * Constants.PIECE_SIZE * 2, 0, Constants.PIECE_SIZE, Constants.BOARD_HEIGHT);
        }
    }
    
    public void handleKeyPress(int key){
        
    }
}
