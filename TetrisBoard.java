import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    int i = 0;
    int x = 0;
    int y = 0;
    
    //Game board array formated in rows x cols (y,x)
    int[][] board = new int[Constants.BOARD_ROWS][Constants.BOARD_COLS];
    
    //Create object variable to hold the current piece in hand
    Tetromino tetromino;
    
    public TetrisBoard() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        board[0][0] = 1;
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawBoard(g);
        tetromino = new Tetromino(i, x,  y);
        tetromino.draw(g);
    }
    
    public void update() {
        i++;
        if (i >= 7) i = 0;
        repaint();
    }

    public void drawBoard(Graphics g) {
        for(int i = 0; i < Constants.BOARD_COLS; i++) {
            for(int j = 0; j < Constants.BOARD_ROWS; j++) {
                Draw.square(i, j, board[j][i], g);
            }   
        }
    }
    
    public void handleKeyPress(int key){
        switch(key){ 
            //left arrow pressed
            case 37:
                x--;
                break;
            //up arrow pressed    
            case 38:
                y--;
                break;
            //right arrow pressed     
            case 39:
                x++;
                break;
            //down arrow pressed     
            case 40:
                y++;
                break;
        }
        repaint();
    }
}
