import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MatrixPanel extends JPanel {
    //Game board array formated in cols x rows (y,x)
    private int[][] board = new int[Constants.BOARD_COLS][Constants.TOTAL_BOARD_ROWS];
    
    //Create object variable to hold the current piece in hand
    Tetromino tetromino;
    
    public MatrixPanel() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        board[0][0] = 1;
        board[6][12] = 3;
        board[4][10] = 5;
        board[2][15] = 7;
        
        initPiece();
    }
    
    public void initPiece() {
        tetromino = new Tetromino((int)(Math.random() * 6), 0, 0, true);
        tetromino.setBoardCoords(5, -1);
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
                            board[indexX][indexY + Constants.BUFFER_ZONE], 
                            g);
            }   
        }
    }
    
    public void handleKeyPress(HashMap<Integer, Boolean> key){
        //left arrow pressed
        if (key.get(KeyEvent.VK_LEFT)) tetromino.tryLeft(board);
        
        //up arrow pressed    
        if (key.get(KeyEvent.VK_UP)) tetromino.tryRotation(board);
        
        //right arrow pressed     
        if (key.get(KeyEvent.VK_RIGHT)) tetromino.tryRight(board);
        
        //down arrow pressed     
        if (key.get(KeyEvent.VK_DOWN)) tetromino.tryDrop(board);
            
        repaint();
    }
}
