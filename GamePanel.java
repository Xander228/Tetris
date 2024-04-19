import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JPanel {

    //Creates an enum with a list of all game states
    private enum GameStates {
        GENERATION_PHASE (0), //Initialize the tetromino object for play
        FALLING_PHASE (1), //Drop the teromino and allow user input
        LOCK_PHASE (2), //Decides whether the tetromino should lock 
        UPDATE_PHASE(3), //Writes tetromino to board
        CLEAR_PHASE(4), //Clears lines & tallies points, lines, and levels
        FINISHED_PHASE (5); //Ends the game

        //GameStates constructor
        GameStates(int typeAsInt){
        }
    }

    private GameStates gameState; //Declare the gameStates
    private MatrixPanel matrixPanel; //Declare the matrixPanel
    private PiecePanel piecePanel; //Declare the piecePanel
    private boolean hasSwap; //Declare the hasSwap variable

    private int score; //Declare the score variable
    private int lines; //Declare the lines variable
    private int level; //Declare the level variable

    //GamePanel constructor
    public GamePanel() {

        this.gameState = GameStates.GENERATION_PHASE; //Sets the initial game state to the first phase, GENERATION_PHASE

        matrixPanel = new MatrixPanel(); //Create the MatrixPanel object
        piecePanel = new PiecePanel(); //Create the PiecePanel object

        //Set up the panel properties
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.PRIMARY_COLOR)); //Add a border around the frame
        setBackground(Constants.PRIMARY_COLOR); //Set the background color of the panel
        setLayout(new BorderLayout(10, 10)); //Sets the edge offset of member panels to properly space them

        // Add the matrixPanel and piecePanel to the panel
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

                piecePanel.updateScores(score,lines,level); //Updates the score panel with new scores
                repaint(); //Redraw everything because things have moved
                break; //Breaks out of the current game phase

            case LOCK_PHASE:
                //Allow user input to move piece right, left, rotate, and drop
                //If hold is pressed, set hasSwap true and return to GENERATION_PHASE
                //Hard drop skips immediately to UPDATE_PHASE
                //If piece is now able to drop revert to FALLING_PHASE
                //If locked continue to UPDATE_PHASE


                matrixPanel.setLowestLock();

                if (keyPressed.get(Constants.HOLD_KEY) == 1 && !hasSwap) {
                    this.hasSwap = true;
                    this.gameState = GameStates.GENERATION_PHASE;
                    break;
                }
                if(keyPressed.get(Constants.HARD_DROP_KEY) == 1) {
                    score += 2 * matrixPanel.hardDrop();
                    this.gameState = GameStates.UPDATE_PHASE;
                    break;
                }

                if(matrixPanel.handleKeyPress(keyPressed)){
                    if(matrixPanel.drop()){
                        score++;
                        this.gameState = GameStates.FALLING_PHASE;
                    } else if(matrixPanel.canResetTimer())
                        matrixPanel.resetTimer();
                }

                if(matrixPanel.isTimerElapsed()){
                    this.gameState = GameStates.UPDATE_PHASE;
                    break;
                }

                matrixPanel.incrementTimer();
                piecePanel.updateScores(score,lines,level);
                repaint();
                break;

            case UPDATE_PHASE:
                //Writes current tetromino to its location on the board
                //Continues to CLEAR_PHASE
                matrixPanel.lockTetromino();
                this.hasSwap = false;
                this.gameState = GameStates.CLEAR_PHASE;
                break;

            case CLEAR_PHASE:

                //checks for lines & clears lines and tallies them
                //Continues to GENERATION_PHASE
                int clearedLines = 0;
                if (matrixPanel.identifyRows()) {
                    matrixPanel.repaint();
                    if(matrixPanel.canClear()) {
                        clearedLines= matrixPanel.clearRows();
                        this.gameState = GameStates.GENERATION_PHASE;
                    }
                } else this.gameState = GameStates.GENERATION_PHASE;
                lines += clearedLines;

                while(true){
                    int levelGoal = 5 * (level * (level + 1)) / 2;
                    if (lines >= levelGoal) level++;
                    else break;
                }

                switch(clearedLines) {
                    case 1:
                        score += 100 * level;
                        break;
                    case 2:
                        score += 300 * level;
                        break;
                    case 3:
                        score += 500 * level;
                        break;
                    case 4:
                        score += 800 * level;
                        break;
                }

                piecePanel.updateScores(score,lines,level);
                if (matrixPanel.lockedAboveBoard()) this.gameState = GameStates.FINISHED_PHASE;
                break;

            case FINISHED_PHASE:
                return false;

        }
        return true;
    }

    public int[] getScores(){
        return new int[]{score, lines, level};
    }
}
