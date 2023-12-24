import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisBoard extends JPanel {
    //Game board array formated in rows x cols (y,x)
    private int[][] board = new int[Constants.BOARD_ROWS][Constants.BOARD_COLS];
    
    //Create object variable to hold the current piece in hand
    Tetromino tetromino;
    
    public TetrisBoard() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        board[0][0] = 1;
        board[6][6] = 3;
        board[10][4] = 5;
        board[15][8] = 7;
        
        initPiece();
    }
    
    public void initPiece() {
        tetromino = new Tetromino((int)(Math.random() * 6), 0, 0, true);
        tetromino.setBoardCoords(5, 0);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawBoard(g);
        tetromino.draw(g);
    }
    
    public void update() {
        repaint();
    }

    public void drawBoard(Graphics g) {
        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                Draw.square(indexX * Constants.PIECE_SIZE, 
                            indexY * Constants.PIECE_SIZE, 
                            board[indexY][indexX], 
                            g);
            }   
        }
    }
    
    public void handleKeyPress(int key){
        switch(key){ 
            //left arrow pressed
            case 37:
                tetromino.tryLeft(board);
                break;
            //up arrow pressed    
            case 38:
                //y--;
                break;
            //right arrow pressed     
            case 39:
                tetromino.tryRight(board);
                break;
            //down arrow pressed     
            case 40:
                tetromino.tryDrop(board);
                break;
        }
        repaint();
    }
}
