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

    private int numberOfResets;

    private int moveTimer;


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
        numberOfResets = 0;
        moveTimer = 0;
    }


    public void resetCounter(){
        numberOfResets = 0;
    }
    public boolean canResetTimer(){
        return numberOfResets <= Constants.MOVES_BEFORE_LOCK;
    }

    public void setLowestLock () {
        tetromino.setLowestLock();
    }

    public boolean canResetCounter () {
        return tetromino.canResetCounter();
    }
    public void resetTimer(){
        moveTimer = 0;
        numberOfResets++;
    }
    public void incrementTimer(){
        moveTimer++;
    }
    public boolean isTimerElapsed(){
        return moveTimer > Constants.MOVES_LOOP_LIMIT;
    }

    public Tetromino getPiece() {
        return this.tetromino;
    }

    public void setPiece(Tetromino tetromino) {
        this.tetromino = tetromino;
        this.tetromino.setBoardRelative(true);
        this.tetromino.setBoardCoords(5, -1);
    }

    public boolean shouldFall(int level){
        double dropMultiplier = isSoftDropping ?
                 Constants.BASE_DROP_TIME :
                 Constants.BASE_FALL_TIME;
        int dropLoops = (int)(dropMultiplier * Math.pow((0.8-((level-1)*0.007)),(level-1))/Constants.LOOP_TIME);

        if(loopsSinceDropped >= dropLoops) {
            loopsSinceDropped = 0;
            return true;
        }
        loopsSinceDropped++;
        return false;
    }

    public boolean drop(){
        return tetromino.tryDrop(board);
    }

    public void hardDrop(){
        tetromino.hardDrop(board);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        this.drawBoard(g);
        if (tetromino == null) return;
        tetromino.drawGhost(board, g);
        tetromino.draw(g);
    }

    public void lockTetromino() {
        tetromino.lock(board);
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
    
    public boolean handleKeyPress(HashMap<Integer, Integer> keyTimes){



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
        isSoftDropping = keyTimes.get(KeyEvent.VK_DOWN) >= 1;

        return successfulMove;
    }
}
