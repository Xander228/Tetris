import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class GamePanel extends JPanel {

    private enum GameStates {
        GENERATION_PHASE (0),
        FALLING_PHASE (1),
        LOCK_PHASE (2),
        CLEAR_PHASE (3),
        UPDATE_PHASE (4),
        FINISHED_PHASE (5);

        private final int type;
        GameStates(int typeAsInt) {
            this.type = typeAsInt;
        }

        public int toInt() {
            return this.type;
        }
    }

    private GameStates gameState;

    private MatrixPanel matrixPanel;
    private PiecePanel piecePanel;
    private boolean hasSwap;

    private int score;
    private int lines;
    private int level;

    public GamePanel() {
        this.gameState = GameStates.GENERATION_PHASE;

        // Create an instance of the TetrisBoard class
        matrixPanel = new MatrixPanel();
        // Create an instance of the ScorePanel class
        piecePanel = new PiecePanel();

        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.ACCENT_COLOR));
        setBackground(Constants.ACCENT_COLOR);
        setLayout(new BorderLayout(10, 10));

        // Add the matrixPanel and piecePanel to the panel
        add(matrixPanel);
        add(piecePanel, BorderLayout.EAST);

        score = 0;
        lines = 0;
        level = 1;

        this.hasSwap = false;
    }

    public void run(HashMap<Integer, Integer> keyPressed) {
        switch(this.gameState){
            case GENERATION_PHASE:
                //Set tetromino to the piece passed in, sets start location
                //If hold check is true switches hold with current piece
                //Continues to FALLING_PHASE
                Tetromino newPiece;
                if(hasSwap) {
                    //swap with hold
                    newPiece = piecePanel.switchPiece(matrixPanel.getPiece());
                    if(newPiece == null) newPiece = piecePanel.getNewPiece();
                } else {
                    //pull from queue
                    newPiece = piecePanel.getNewPiece();
                }
                this.matrixPanel.setPiece(newPiece);
                this.gameState = GameStates.FALLING_PHASE;
                break;

            case FALLING_PHASE:
                //Allow user input to move piece right, left, rotate, and drop; also drops each x seconds based on level
                //If hold is pressed, set hold check true and return to GENERATION_PHASE
                //checks for piece underneath current tetromino and sets state to LOCK_PHASE if true
                //Hard drop skips to CLEAR_PHASE
                if (keyPressed.get(Constants.HOLD_KEY) == 1 && !hasSwap) {
                    this.hasSwap = true;
                    this.gameState = GameStates.GENERATION_PHASE;
                    break;
                }
                if(keyPressed.get(Constants.HARD_DROP_KEY) == 1) {
                    matrixPanel.hardDrop();
                    this.gameState = GameStates.CLEAR_PHASE;
                }

                matrixPanel.handleKeyPress(keyPressed);

                if(matrixPanel.shouldFall(1)){
                    if(matrixPanel.drop()){
                        //score++
                    } else {
                        if(matrixPanel.canResetCounter()) {
                            matrixPanel.resetTimer();
                            matrixPanel.resetCounter();
                        }
                        this.gameState = GameStates.LOCK_PHASE;
                        break;
                    }
                }

                matrixPanel.repaint();
                break;

            case LOCK_PHASE:
                //Allow user input to move piece right, left, rotate, and lock; force locks after x seconds
                //If hold is pressed, set hold check true and return to GENERATION_PHASE
                //If piece is now able to drop revert to FALLING_PHASE
                //If locked continue to CLEAR_PHASE
                matrixPanel.setLowestLock();

                if (keyPressed.get(Constants.HOLD_KEY) == 1 && !hasSwap) {
                    this.hasSwap = true;
                    this.gameState = GameStates.GENERATION_PHASE;
                    break;
                }
                if(keyPressed.get(Constants.HARD_DROP_KEY) == 1) {
                    matrixPanel.hardDrop();
                    this.gameState = GameStates.CLEAR_PHASE;
                }

                if(matrixPanel.handleKeyPress(keyPressed)){
                    if(matrixPanel.drop()){
                        //score++
                        this.gameState = GameStates.FALLING_PHASE;
                    } else if(matrixPanel.canResetTimer())
                        matrixPanel.resetTimer();
                }

                if(matrixPanel.isTimerElapsed()){
                    this.gameState = GameStates.CLEAR_PHASE;
                    break;
                }

                matrixPanel.incrementTimer();
                matrixPanel.repaint();
                break;

            case CLEAR_PHASE:
                //Writes current tetromino to its location on the board
                //Continues to UPDATE_PHASE
                matrixPanel.lockTetromino();
                this.hasSwap = false;
                this.gameState = GameStates.UPDATE_PHASE;
                break;

            case UPDATE_PHASE:

                //checks for lines & clears lines and tallies them
                //Continues to UPDATE_PHASE
                if (matrixPanel.identifyRows()) {
                    matrixPanel.repaint();
                    if(matrixPanel.canClear()) {
                        lines += matrixPanel.clearRows();
                        this.gameState = GameStates.GENERATION_PHASE;
                    }
                } else this.gameState = GameStates.GENERATION_PHASE;

                while(true){
                    int levelGoal = 5 * (level * (level + 1)) / 2;
                    if (lines >= levelGoal) level++;
                    else break;
                }

                piecePanel.updateScores(score,lines,level);
                break;
        }
    }
}
