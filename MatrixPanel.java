import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MatrixPanel extends JPanel {
    //Game board array formated in cols x rows (y,x)
    private int[][] board = new int[Constants.BOARD_COLS][Constants.TOTAL_BOARD_ROWS];
    
    //Create object variable to hold the current piece in hand
    Tetromino tetromino;

    //Create a counter to count the number of gameLoops elapsed since the last drop
    private int loopsSinceDropped;
    private boolean isSoftDropping;
    private boolean isHardDropping;
    public MatrixPanel() {
        // Initialize components, set layout
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, 
                                        Constants.BOARD_HEIGHT));
        board[0][0] = 1;
        board[6][12] = 3;
        board[4][10] = 5;
        board[2][15] = 7;

        loopsSinceDropped = 0;
        isSoftDropping = false;
        isHardDropping = false;

        initPiece();
    }
    
    private void initPiece() {
        tetromino = new Tetromino((int)(Math.random() * 6), 0, 0, true);
        tetromino.setBoardCoords(5, -1);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawBoard(g);
        tetromino.drawGhost(board, g);
        tetromino.draw(g);
    }
    
    public void update() {
        repaint();
        loopsSinceDropped++;
    }

    private void drawBoard(Graphics g) {
        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                Draw.square(indexX * Constants.PIECE_SIZE, 
                            indexY * Constants.PIECE_SIZE, 
                            board[indexX][indexY + Constants.BUFFER_ZONE], 
                            g);
            }   
        }
    }
    
    private void handleKeyPress(HashMap<Integer, Boolean> key){

        boolean successfulMove = false;
        //if left arrow pressed tryLeft and if successful set successfulMove true if it's not already
        if (key.get(KeyEvent.VK_LEFT)) successfulMove |= tetromino.tryLeft(board);
        
        //if up arrow pressed tryRotation and if successful set successfulMove true if it's not already
        if (key.get(KeyEvent.VK_UP)) successfulMove |= tetromino.tryRotation(board);
        
        //if right arrow pressed tryRight and if successful set successfulMove true if it's not already
        if (key.get(KeyEvent.VK_RIGHT)) successfulMove |= tetromino.tryRight(board);
        
        //if down arrow pressed or released set isSoftDropping to the down arrow's current state
        isSoftDropping = key.get(KeyEvent.VK_DOWN);

        repaint();
    }
}
