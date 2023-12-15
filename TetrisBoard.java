import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
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
            tetromino = new Tetromino(i,0,i*2);
            tetromino.draw(g);
        }
        Draw.Square(0, 40, Color.RED, g);
        Draw.Square(40, 40, Color.ORANGE, g);
        Draw.Square(80, 40, Color.YELLOW, g);
        Draw.Square(120, 40, Color.GREEN, g);
        Draw.Square(160, 40, Color.CYAN, g);
        Draw.Square(200, 40, Color.BLUE, g);
        Draw.Square(240, 40, Color.MAGENTA, g);
    }
    
    public void update() {

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
        switch(key){
            case 38:
                
                break;
                
            case 40:
                
                break;
                
            case 37:
                
                break;
                
            case 39:
                
                break;
        }
    }
}
