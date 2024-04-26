import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MatrixPanel extends JPanel {
    private int[][] board = new int[Constants.BOARD_COLS][Constants.TOTAL_BOARD_ROWS]; //Game board array formatted in cols x rows (x,y)

    private Tetromino tetromino; //Declare tetromino variable to hold the current piece in hand

    private int loopsSinceDropped; //Declares an int to store the number of game loops elapsed since the last drop
    private boolean isSoftDropping; //Declares a boolean to store whether the piece is currently soft dropping
    private int numberOfResets; //Declares an int to store the number of resets performed on a locking piece
    private int moveTimer; //Declares an int to store the time, in loops, since a piece started locking
    private int loopsSinceIdentified; //Declares an int to store the time, in loops, since the lines were first identified


    public MatrixPanel() {
        setPreferredSize( new Dimension(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT)); //Sets the size of the board panel

        loopsSinceDropped = 0; //Initializes the number of loops since the last drop to 0
        isSoftDropping = false; //Initializes the boolean of whether the piece is currently soft dropping to false
        numberOfResets = 0; //Initializes the number of resets performed on a locking piece to 0
        moveTimer = 0; //Initializes the time, in loops, since a piece started locking to 0
        loopsSinceIdentified = 0; //Initializes the time, in loops, since the lines were first identified to 0
    }


    //Resets the counter that stores the number of resets performed on a locking piece to 0
    public void resetCounter(){
        numberOfResets = 0;
    }

    //Resets the timer if the number of resets is less than or equal to the number of moves before the piece should be locked
    public void tryResettingTimer(){
        if (numberOfResets <= Constants.MOVES_BEFORE_LOCK) resetTimer();
    }

    //Sets the current tetrominos lowest lock height
    public void setLowestLock () {
        tetromino.setLowestLock();
    }

    //Gets whether the current tetromino allows for the reset counter to be reset
    public boolean canResetCounter () {
        return tetromino.canResetCounter();
    }

    //Resets the piece lock timer and increments the number of resets counter to indicate the timer was reset
    public void resetTimer(){
        moveTimer = 0;
        numberOfResets++;
    }

    //Increments the timer that tracks the number of loops since a piece started locking
    public void incrementTimer(){
        moveTimer++;
    }

    //Checks whether the timer that tracks whether the piece should be locked is expired
    public boolean isTimerElapsed(){
        return moveTimer > Constants.MOVES_LOOP_LIMIT;
    }

    //Gets the current tetromino in the hand
    public Tetromino getPiece() {
        return this.tetromino;
    }

    //Sets and initializes the current tetromino in hand
    public void setPiece(Tetromino tetromino) {
        this.tetromino = tetromino; //Sets the current tetromino to the object passed in
        this.tetromino.setBoardRelative(true); //Sets the current tetromino's coordinate mode to board relative
        this.tetromino.setBoardCoords(5, -1); //Set's the current tetromino's coordinates to top center

        //If the piece is overlaping when spawned, start moving the piece above the board until it is no longer touching
        int startOffset = 1; //Sets the initial starting height offset to 1 above its previous position
        while(tetromino.isOverlapped(board)) {
            tetromino.setBoardCoords(5, -1 - startOffset); //Try the new board height
            startOffset++; //Increase the offset for nex loop
        }
    }

    //Checks whether the piece should fall during the current game loop based on the current level
    public boolean shouldFall(int level){

        //If the player is soft dropping, set the drop multiplier to the base drop time, otherwise set it to the base fall time
        double dropMultiplier = isSoftDropping ?
                 Constants.BASE_DROP_TIME :
                 Constants.BASE_FALL_TIME;

        //Calculate the number of loops before the tetromino should drop again based on the official Tetris equation
        int dropLoops = (int)(dropMultiplier * Math.pow((0.8-((level-1)*0.007)),(level-1))/Constants.LOOP_TIME);

        //If the number of loops since last dropped is equal to or greater than the number of loops require to drop, then drop and reset the counter
        if(loopsSinceDropped >= dropLoops) {
            loopsSinceDropped = 0; //Reset the counter
            return true; //Return true to indicate the piece should be dropped
        }
        loopsSinceDropped++; //Otherwise increment the number of loops since dropped
        return false; //return false to indicate the piece should not drop this loop
    }

    //Try to drop the piece and return true if successful
    public boolean drop(){
        return tetromino.tryDrop(board);
    }

    //Check if the player is soft dropping
    public boolean isSoftDropping(){
        return isSoftDropping;
    }

    //Try to hard drop the piece and return true if successful
    public int hardDrop(){
        return tetromino.hardDrop(board);
    }

    //Override the paint component method inherited from JPanel in order to draw board and tetrominos
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //Run the inherited paintComponent code
        this.drawBoard(g); //Draw the current board state
        if (tetromino == null) return; //If the tetromino does not exist yet, stop drawing
        tetromino.drawGhost(board, g); //Draw the ghost piece
        tetromino.draw(g); //Draw the current piece
    }

    //Writes the current tetromino to the board and hide the current tetromino object
    public void lockTetromino() {
        tetromino.lock(board);
    }

    //Highlight full rows with white and return whether there are rows to clear
    public boolean identifyRows(){
        boolean rowsToClear = false; //Initializes rows to clear to false

        //Index through the y-axis of the board to check up by row
        for (int indexY = Constants.TOTAL_BOARD_ROWS - 1; indexY >= Constants.BUFFER_ZONE; indexY--) {
            boolean rowFull = true; //Initializes rows full to true
            boolean rowEmpty = true; //Initializes rows empty to true

            //Index through the x-axis of the board to check through the row
            for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                if (board[indexX][indexY] == 0) rowFull = false; //If any cell in the row has an empty cell, rowFull becomes false
                if (board[indexX][indexY] != 0) rowEmpty = false; //If any cell int the row has a full cell, rowEmpty becomes false
            }
            if (rowEmpty) break; //Breaks out of the y-axis for loop early if the row is completely clear because by extent, all rows above are clear as well

            //If a row is full it should be highlighted with white and rowsToClear should be true
            if (rowFull) {
                for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) board[indexX][indexY] = 8; //For every cell in the row, set it's color to white
                rowsToClear = true; //Sets rowsToClear true because there are rows that need to be cleared
            }
        }
        repaint(); //Redraw everything because the board has changed
        return rowsToClear; //Return whether there are rows that need to be cleared
    }

    //Actually clears the rows and returns the number of rows that were cleared
    public int clearRows() {

        int lines = 0; //Initializes the number of cleared lines to  0

        //Index through the y-axis of the board to check up by row
        for (int indexY = Constants.BUFFER_ZONE; indexY < Constants.TOTAL_BOARD_ROWS; indexY++) {
            boolean rowFull = true; //Initializes rowsFull to true
            //Index through the x-axis of the board to check through the row
            for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                //If any cell in the row has an empty cell, rowFull becomes false
                if (board[indexX][indexY] == 0) {
                    rowFull = false; //Set rowFull false
                    break; //Break out of this for loop and skip the next one because rowFull is false, continues onto next row
                }
            }

            //If there is a full row, clear it and move the rest of the board above down by one cell
            if (rowFull) {
                //Index through the y-axis, starting at the current index that the row was last identified
                for (int writeY = indexY; writeY >= 0; writeY--) {
                    boolean rowFinish = true; //Initializes rowFinish to true
                    //Index through the x-axis of the board and moves each cell down by one
                    for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
                        board[indexX][writeY] = board[indexX][writeY - 1]; //Set the current cell to the one above
                        if (board[indexX][writeY] != 0) rowFinish = false; //If the cell that was copied isn't empty, then set rowFinish false to keep running it
                    }
                    if (rowFinish) break; //Once rowFinish remains true, the for loop can break back into the main loop and find the next full row
                }
                lines++; //Add one to lines to tally that another line has been cleared
            }
        }
        loopsSinceIdentified = 0; //Reset loops since identified to zero to reset for the next time that lines are cleared
        return lines; //Return the number of lines that were cleared
    }

    //Checks whether the game is allowed to clear full lines after they're identified
    public boolean canClear() {
        loopsSinceIdentified++; //Increment the counter by one, to count the next loop
        return loopsSinceIdentified >= Constants.CLEAR_LOOPS; //If enough loops have passed, return that the rows are allowed to be cleared
    }

    //Checks whether any cells above the playable area are occupied to trigger the game end
    public boolean lockedAboveBoard() {
        //Index through the row above the top visible row of the board
        for (int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            if (board[indexX][Constants.BUFFER_ZONE - 1] != 0) return true; //Return true if any of the cells are occupied
        }
        return false; //Return false as long as there are no cells occupied
    }

    //Indexes through and draw the current board state
    private void drawBoard(Graphics g) {
        //Index through the x-axis of the board
        for(int indexX = 0; indexX < Constants.BOARD_COLS; indexX++) {
            //Index through the y-axis of the board
            for(int indexY = 0; indexY < Constants.BOARD_ROWS; indexY++) {
                //Draw the cell at the current location
                Draw.square(indexX * Constants.CELL_SIZE,
                            indexY * Constants.CELL_SIZE,
                            board[indexX][indexY + Constants.BUFFER_ZONE],
                            g);
            }   
        }
    }

    //Handle the action associated with certain key presses
    public boolean handleKeyPress(HashMap<Integer, Integer> keyTimes){

        boolean successfulMove = false; //Initialize the successfulMove variable to false

        //If left arrow pressed tryLeft and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_LEFT) == 1 || keyTimes.get(KeyEvent.VK_LEFT) >= Constants.AUTO_MOVE_LOOPS)
            successfulMove |= tetromino.tryLeft(board);
        
        //If right arrow pressed tryRight and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_RIGHT) == 1 || keyTimes.get(KeyEvent.VK_RIGHT) >= Constants.AUTO_MOVE_LOOPS)
            successfulMove |= tetromino.tryRight(board);

        //If up arrow pressed tryRotatingCW and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_UP) == 1 )
            successfulMove |= tetromino.tryRotatingCW(board);

        //If up arrow pressed tryRotatingCW and if successful set successfulMove true if it's not already
        if (keyTimes.get(KeyEvent.VK_CONTROL) == 1 )
            successfulMove |= tetromino.tryRotatingCCW(board);

        //If down arrow pressed or released set isSoftDropping to the down arrow's current state
        isSoftDropping = keyTimes.get(KeyEvent.VK_DOWN) >= 1;

        return successfulMove; //Return whether a successful move has been performed
    }
}
