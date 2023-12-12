import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    private JSlider boardSlider;
    int j = 0;
    public static int i = 0;
    public TetrisBoard() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        boardSlider = new JSlider();
        add(boardSlider);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        i = 10;
        this.drawGrid(g);
        i = j;
        Square.draw(0, 40, 1, g);
        Square.draw(40, 40, 2, g);
        Square.draw(80, 40, 3, g);
        Square.draw(120, 40, 4, g);
        Square.draw(160, 40, 5, g);
        Square.draw(200, 40, 6, g);
        Square.draw(240, 40, 7, g);
    }
    
    public void update() {
        
        j = boardSlider.getValue();
    }
    
    public void drawBoard(Graphics g) {

    }

    public void drawGrid(Graphics g) {
        for(int i = 0; i < Constants.BOARD_COLS; i++) {
            for(int j = 0; j < Constants.BOARD_ROWS; j++) {
                Square.draw(i * Constants.PIECE_SIZE, j * Constants.PIECE_SIZE, 0, g);
            }   
        }
    }
    
    public void drawLogo(Graphics g) {
        int[][] logo = {
            {1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,1},
            {1 ,3 ,3 ,3 ,5 ,5 ,5 ,7 ,7 ,7 ,9 ,9 ,2 ,2 ,11,13,13,13,1},
            {1 ,2 ,3 ,2 ,5 ,2 ,2 ,2 ,7 ,2 ,9 ,2 ,9 ,2 ,11,13,2 ,2 ,1},
            {1 ,2 ,3 ,2 ,5 ,5 ,2 ,2 ,8 ,2 ,10,10,2 ,2 ,11,2 ,13,2 ,1},
            {1 ,2 ,4 ,2 ,6 ,2 ,2 ,2 ,8 ,2 ,10,2 ,10,2 ,12,2 ,2 ,14,1},
            {1 ,2 ,4 ,2 ,6 ,6 ,6 ,6 ,8 ,2 ,10,2 ,2 ,10,12,14,14,14,1},
            {1 ,1 ,1 ,1 ,1 ,1 ,1 ,2 ,2 ,2 ,2 ,2 ,1 ,1 ,1 ,1 ,1 ,1 ,1},
            {0 ,0 ,0 ,0 ,0 ,0 ,1 ,2 ,2 ,2 ,2 ,2 ,1 ,0 ,0 ,0 ,0 ,0 ,0},
            {0 ,0 ,0 ,0 ,0 ,0 ,1 ,2 ,2 ,2 ,2 ,2 ,1 ,0 ,0 ,0 ,0 ,0 ,0},
            {0 ,0 ,0 ,0 ,0 ,0 ,1 ,2 ,2 ,2 ,2 ,2 ,1 ,0 ,0 ,0 ,0 ,0 ,0},
            {0 ,0 ,0 ,0 ,0 ,0 ,1 ,2 ,2 ,2 ,2 ,2 ,1 ,0 ,0 ,0 ,0 ,0 ,0},
            {0 ,0 ,0 ,0 ,0 ,0 ,1 ,2 ,2 ,2 ,2 ,2 ,1 ,0 ,0 ,0 ,0 ,0 ,0},
            {0 ,0 ,0 ,0 ,0 ,0 ,1 ,1 ,1 ,1 ,1 ,1 ,1 ,0 ,0 ,0 ,0 ,0 ,0},
        };
        g.setColor(new Color((int)0x21477d).brighter().brighter());
        for(int i = 0; i < logo.length ; i++) {
            for(int j = 0; j < logo[0].length ; j++) {
                g.setColor(new Color(20000 * logo[i][j]));
                g.fillRect(10 * j, 10 * i, 10, 10);
            }
        }
    }
    public void handleKeyPress(int key){
        
    }
}
