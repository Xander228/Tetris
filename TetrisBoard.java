import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    int x = 0;
    int y = 0;
    Tetromino tetromino;
    public TetrisBoard() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));

    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawGrid(g);
        for(int i = 0; i < 7; i++){
            tetromino = new Tetromino(i, x, i * 2 + y);
            tetromino.draw(g);
        }

    }
    
    public void update() {
        repaint();
        y++;
        if(y > 10) {
            y = 0;
            
        }
    }
    
    public void drawBoard(Graphics g) {

    }

    public void drawGrid(Graphics g) {
        for(int i = 0; i < Constants.BOARD_COLS; i++) {
            for(int j = 0; j < Constants.BOARD_ROWS; j++) {
                Draw.square(i, j, 0, g);
            }   
        }
    }
    
    public void handleKeyPress(int key){
        switch(key){ 
            case 37:
                x--;
                repaint();
                break;
                
            case 38:
                y--;
                repaint();
                break;
                
            case 39:
                x++;
                repaint();
                break;
                
            case 40:
                y++;
                repaint();
                break;
        }
    }
}
