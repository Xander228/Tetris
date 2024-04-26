import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JPanel {

    //Creates an enum with a list of all game states
    private enum GameStates {
        GENERATION_PHASE (), //Initialize the tetromino object for play
        FALLING_PHASE (), //Drop the teromino and allow user input
        LOCK_PHASE (), //Decides whether the tetromino should lock
        UPDATE_PHASE(), //Writes tetromino to board
        CLEAR_PHASE(), //Clears lines & tallies points, lines, and levels
        FINISHED_PHASE (); //Ends the game

        //GameStates constructor
        GameStates(){}
    }

    private GameStates gameState; //Declare the gameStates to store the current state of the game
    final private MatrixPanel matrixPanel; //Declare the matrixPanel to store the board
    final private PiecePanel piecePanel; //Declare the piecePanel to store the score, hold, and queue panels
    private boolean hasSwap; //Declare a boolean to store whether the piece has or should be swapped

    private int score; //Declare an int to store the score
    private int lines; //Declare an int to store the lines cleared
    private int level; //Declare an int to store the current level

    public GamePanel() {

        this.gameState = GameStates.GENERATION_PHASE; //Sets the initial game state to the first phase, GENERATION_PHASE

        matrixPanel = new MatrixPanel(); //Create the MatrixPanel object
        piecePanel = new PiecePanel(); //Create the PiecePanel object

        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.PRIMARY_COLOR)); //Add a border around the frame
        setBackground(Constants.PRIMARY_COLOR); //Set the background color of the panel
        setLayout(new BorderLayout(10, 10)); //Sets the edge offset of member panels to properly space them

        add(matrixPanel); //Adds the matrixPanel object to the center of the parent panel
        add(piecePanel, BorderLayout.EAST); //Adds the piecePanel object to the right of the parent panel

        score = 0; //Initializes the score to 0
        lines = 0; //Initializes the lines to 0
        level = 1; //Initializes the level to 1
        this.hasSwap = false; //Initializes the hasSwap to false
    }

    //The run method is called every game loop by the timer in the TetrisFrame
    //It accepts the map of key times for user input
    public boolean run(HashMap<Integer, Integer> keyPressed) {

        //switch statement to decide what code to run based on current game state
        switch(this.gameState){

            case GENERATION_PHASE:
                //Set tetromino to the piece passed in, sets start location
                //If hold check is true switches hold with current piece
                //Continues to FALLING_PHASE at the end

                Tetromino newPiece; //Declare local variable newPiece to store the new tetromino object

                //If hasSwap is true, that means that a piece was requested to be held, and it should execute the code to run swap them, otherwise pull a new piece
                 if(hasSwap) {
                    //swap current piece with hold piece
                    newPiece = piecePanel.switchPiece(matrixPanel.getPiece()); //Set the hold piece to the current piece and the current piece to the held piece
                    if(newPiece == null) newPiece = piecePanel.getNewPiece(); //If it has received a null held piece, then it pull the next piece from queue
                } else {
                    //pull piece from queue
                    newPiece = piecePanel.getNewPiece(); //Sets the newPiece to a piece pulled from queue
                }
                this.matrixPanel.setPiece(newPiece); //Sets the active piece in matrixPanel to the newPiece
                this.gameState = GameStates.FALLING_PHASE; //Sets the next game phase to FALLING_PHASE
                break; //Breaks out of the current game phase

            case FALLING_PHASE:
                //Allow user input to move piece right, left, rotate, and drop
                //If hold is pressed, set hasSwap true and return to GENERATION_PHASE
                //Hard drop skips immediately to UPDATE_PHASE after dropping the piece
                //Checks for piece underneath current tetromino and sets state to LOCK_PHASE if true

                //If the hold key is pressed and a swap has not been requested already then swap pieces
                //If hasSwap is true, that means a piece has been held since the last lock, and it can't switch again
                //Checks if the key time equals 1 because this should only be triggered once per keypress
                if (keyPressed.get(Constants.HOLD_KEY) == 1 && !hasSwap) {
                    this.hasSwap = true; //Preps the GENERATION_PHASE for a swap and prevents additional holds before a lock
                    this.gameState = GameStates.GENERATION_PHASE; //Sets the next game phase to GENERATION_PHASE
                    break; //Breaks out of the current game phase
                }

                //If the hard drop key is pressed the hard drop the piece
                //Checks if the key time equals 1 because this should only be triggered once per keypress
                if(keyPressed.get(Constants.HARD_DROP_KEY) == 1) {
                    score += 2 * matrixPanel.hardDrop(); //Runs hardDrop which drops the piece and returns the number of lines dropped to award points
                    this.gameState = GameStates.UPDATE_PHASE; //Sets the next game phase to UPDATE_PHASE
                    break; //Breaks out of the current game phase
                }

                matrixPanel.handleKeyPress(keyPressed); //Passes in pressed keys to matrixPanel to handle right, left, rotate, and drop movement

                //Determines whether the fall time has elapsed based on current level
                if(matrixPanel.shouldFall(level)){
                    //Attempts to drop the piece and returns true if successful
                    if(matrixPanel.drop()){
                        if(matrixPanel.isSoftDropping()) score++; //Adds a bonus score of 1 per line if soft dropping
                    } else {
                        //If the piece did not drop then reset lock timers and continue to lock phase

                        //If the piece has fallen below its previous lock point, lock counter and timer are reset
                        if(matrixPanel.canResetCounter()) {
                            matrixPanel.resetTimer(); //Resets the timer that determines when to lock the piece
                            matrixPanel.resetCounter(); //Resets the counter that determines how many times the timer can be reset
                        }
                        this.gameState = GameStates.LOCK_PHASE; //Sets the next game phase to LOCK_PHASE
                        break; //Breaks out of the current game phase
                    }
                }

                piecePanel.updateScores(score,lines,level); //Updates the score panel with new soft or hard drop related scores
                repaint(); //Redraw everything because things have moved
                break; //Breaks out of the current game phase

            case LOCK_PHASE:
                //Allow user input to move piece right, left, rotate, and drop
                //If hold is pressed, set hasSwap true and return to GENERATION_PHASE
                //Hard drop skips immediately to UPDATE_PHASE
                //If piece is now able to drop revert to FALLING_PHASE
                //If locked continue to UPDATE_PHASE

                matrixPanel.setLowestLock(); //Records the height of the lock attempt to determine if the lock counter can reset

                //If the hold key is pressed and a swap has not been requested already then swap pieces
                //If hasSwap is true, that means a piece has been held since the last lock, and it can't switch again
                //Checks if the key time equals 1 because this should only be triggered once per keypress
                if (keyPressed.get(Constants.HOLD_KEY) == 1 && !hasSwap) {
                    this.hasSwap = true; //Preps the GENERATION_PHASE for a swap and prevents additional holds before a lock
                    this.gameState = GameStates.GENERATION_PHASE; //Sets the next game phase to GENERATION_PHASE
                    break; //Breaks out of the current game phase
                }

                //If the hard drop key is pressed the hard drop the piece
                //Checks if the key time equals 1 because this should only be triggered once per keypress
                if(keyPressed.get(Constants.HARD_DROP_KEY) == 1) {
                    score += 2 * matrixPanel.hardDrop(); //Runs hardDrop which drops the piece and returns the number of lines dropped to award points
                    this.gameState = GameStates.UPDATE_PHASE; //Sets the next game phase to UPDATE_PHASE
                    break; //Breaks out of the current game phase
                }

                //If the user's key presses resulted in a successful left, right, or rotation move, try dropping and resetting the timer
                if(matrixPanel.handleKeyPress(keyPressed)){

                    //Try dropping the piece, if successful then return to FALLING_PHASE, otherwise try to rest the timer
                    if(matrixPanel.drop()){
                        if(matrixPanel.isSoftDropping()) score++; //Adds a bonus point if soft dropping
                        this.gameState = GameStates.FALLING_PHASE; //Sets the next game phase back to FALLING_PHASE
                        break; //Breaks out of the current game phase
                    } else matrixPanel.tryResettingTimer(); //Check if the timer has been reset too many times and only resets if it's below the threshold
                }

                //If the timer has run out, lock the piece
                if(matrixPanel.isTimerElapsed()){
                    this.gameState = GameStates.UPDATE_PHASE; //Sets the next game phase to UPDATE_PHASE
                    break; //Breaks out of the current game phase
                }

                matrixPanel.incrementTimer(); //Increment the timer by one loop time
                piecePanel.updateScores(score,lines,level); //Updates the score panel with new soft or hard drop related scores
                repaint(); //Redraw everything because things have moved
                break; //Breaks out of the current game phase

            case UPDATE_PHASE:
                //Writes current tetromino to its current location on the board
                //Continues to CLEAR_PHASE at the end

                matrixPanel.lockTetromino(); //Tells the matrixPanel to write the tetromino to the board
                this.hasSwap = false; //Tells the GENERATION_PHASE that it should not swap pieces and allows pieces to be swapped after this lock
                this.gameState = GameStates.CLEAR_PHASE; //Sets the next game phase to UPDATE_PHASE
                break; //Breaks out of the current game phase

            case CLEAR_PHASE:
                //Indexes through the board to look for lines, tallies them, and then clears them
                //Returns back to GENERATION_PHASE at the end

                int clearedLines = 0; //Declares and initializes a variable to store the number of cleared line

                //Identifies full rows of pieces and colors them white, then breaks until it is allowed to clear them
                if (matrixPanel.identifyRows()) {

                    //If the right number of loops have passed it allows the lines to be cleared, otherwise it keeps breaking
                    if(matrixPanel.canClear()) {
                        clearedLines = matrixPanel.clearRows(); //Actually clear the lines and tallies the total for scoring
                    } else break; //If the game can't clear the lines, break and restart the CLEAR_PHASE
                }

                lines += clearedLines; //Adds the number of lines just cleared to the total tally

                //Increments the level based on whether the current number of lines have met the goal for that level
                while(true){
                    int levelGoal = 5 * (level * (level + 1)) / 2; //Sets the new goal needed for the next level based on the current level
                    if (lines >= levelGoal) level++; //If the number of lines cleared has exceeded that goal, the level is increased
                    else break; //Otherwise continue the rest of the phase by breaking out of the while loop
                }

                //Decides how many points to award based on the number of lines that were cleared
                switch(clearedLines) {
                    case 1:
                        score += 100 * level; //1 cleared line gives 100 points times the current level
                        break;
                    case 2:
                        score += 300 * level; //2 cleared lines gives 300 points times the current level
                        break;
                    case 3:
                        score += 500 * level; //3 cleared lines gives 500 points times the current level
                        break;
                    case 4:
                        score += 800 * level; //4 cleared lines gives 800 points times the current level
                        break;
                }

                piecePanel.updateScores(score,lines,level); //Updates the score panel with new line and level based scores
                this.gameState = GameStates.GENERATION_PHASE; //Sets the next game phase to GENERATION_PHASE
                if (matrixPanel.lockedAboveBoard()) this.gameState = GameStates.FINISHED_PHASE; //If a piece locks above the board, sets the next game phase to FINISHED_PHASE
                break; //Breaks out of the current game phase

            case FINISHED_PHASE:
                //Purpose of this phase is to ensure that whenever this method is called after the game has ended, it will always return false
                return false; //Returns false to indicate the game is over

        }
        return true; //Returns true to indicate the game is still running
    }

    //Getter method to retrieve scores from the gamePanel
    public int[] getScores(){
        return new int[]{score, lines, level}; //Returns an int array of the scores
    }
}
