import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GamePanel extends JPanel {

    private enum GameStates {
        GENERATION_PHASE (0),
        FALLING_PHASE (1),
        LOCK_PHASE (2),
        CLEAR_PHASE (3),
        UPDATE_PHASE (4);

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

    public GamePanel() {
        this.gameState = GameStates.FALLING_PHASE;

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

        this.hasSwap = false;
    }

    public void runGameLoop(HashMap<Integer, Boolean> keyPressed) {
        switch(this.gameState){
            case GENERATION_PHASE:
                //Set tetromino to the piece passed in, sets start location
                //If hold check is true switches hold with current piece
                //Continues to FALLING_PHASE
                if(hasSwap) {
                    //swap with hold

                } else {
                    //pull from queue
                }
                break;
            case FALLING_PHASE:
                //Allow user input to move piece right, left, rotate, and drop; also drops each x seconds based on level
                //If hold is pressed, set hold check true and return to GENERATION_PHASE
                //checks for piece underneath current tetromino and sets state to LOCK_PHASE if true
                //Hard drop skips to CLEAR_PHASE
                matrixPanel.update(keyPressed);

                break;
            case LOCK_PHASE:
                //Allow user input to move piece right, left, rotate, and lock; force locks after x seconds
                //If hold is pressed, set hold check true and return to GENERATION_PHASE
                //If piece is now able to drop revert to FALLING_PHASE
                //If locked continue to CLEAR_PHASE
                matrixPanel.update(keyPressed);
                break;
            case CLEAR_PHASE:
                //Writes current tetromino to its location on the board and checks for lines
                //Clears lines and tallies them
                //Continues to UPDATE_PHASE
                break;
            case UPDATE_PHASE:
                //Updates score, lines, and level
                //Resets hold check
                //Continues to GENERATION_PHASE
                break;
        }
    }
}
