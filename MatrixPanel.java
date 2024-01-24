import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MatrixPanel extends JPanel {
    //Game board array formatted in cols x rows (y,x)
    private int[][] board = new int[Constants.BOARD_COLS][Constants.TOTAL_BOARD_ROWS];
    
    //Create object variable to hold the current piece in hand
    private Tetromino tetromino;

    //Create a counter to count the number of gameLoops elapsed since the last drop
    private int loopsSinceDropped;
    private boolean isSoftDropping;

    //create a hashmap that maps a key to an integer value that represents the number of loops the key has been pressed for
    HashMap<Integer, Integer> keyTimes = new HashMap<Integer, Integer>();

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

        //adds each value of keyList to the hashmap
        for(int key : Constants.KEY_LIST) keyTimes.put(key, 0);
    }


    public Tetromino getPiece() {
        return this.tetromino;
    }

    public void setPiece(Tetromino tetromino) {
        this.tetromino = tetromino;
        this.tetromino.setBoardRelative(true);
        this.tetromino.setBoardCoords(5, -1);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawBoard(g);
        if (tetromino == null) return;
        tetromino.drawGhost(board, g);
        tetromino.draw(g);
    }
    
    public void update(HashMap<Integer, Boolean> key) {
        handleKeyPress(key);
        loopsSinceDropped++;

        repaint();
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
    
    private boolean handleKeyPress(HashMap<Integer, Boolean> keyPressed){

        for(int key : Constants.KEY_LIST) {
            if(keyPressed.get(key)) keyTimes.replace(key,1 + keyTimes.get(key));
            else keyTimes.replace(key, 0);
        }

        boolean successfulMove = false;
        //if left arrow pressed tryLeft and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_LEFT) == 1 || keyTimes.get(KeyEvent.VK_LEFT) >= Constants.AUTO_MOVE_LOOPS)
            successfulMove |= tetromino.tryLeft(board);
        
        //if right arrow pressed tryRight and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_RIGHT) == 1 || keyTimes.get(KeyEvent.VK_RIGHT) >= Constants.AUTO_MOVE_LOOPS)
            successfulMove |= tetromino.tryRight(board);

        //if up arrow pressed tryRotation and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_UP) == 1 )
            successfulMove |= tetromino.tryRotation(board);
        
        //if down arrow pressed or released set isSoftDropping to the down arrow's current state
        isSoftDropping = keyPressed.get(KeyEvent.VK_DOWN);

        return successfulMove;
    }
}
