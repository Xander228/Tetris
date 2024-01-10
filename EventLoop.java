import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class EventLoop
{
    
    private enum ScreenStates {
        HOME (0),
        STARTING (1),
        RUNNING (2),
        PAUSED (3);
        
        private final int type; 
        ScreenStates(int typeAsInt) {
            this.type = typeAsInt;
        }
        
        public int toInt() {
            return this.type;
        }
    }
    
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

    private ScreenStates screenState;
    private GameStates gameState;
    
    public EventLoop()
    {
        this.screenState = ScreenStates.RUNNING;
        this.gameState = GameStates.GENERATION_PHASE;
    }
    
    public void run(HashMap<Integer, Boolean> key) {
        switch(this.screenState){
            case HOME:
                //Display home panel with a play and quit button
                break;
            case STARTING:
                //Display starting panel and then Sets state to Running 
                break;
            case RUNNING:
                //Display Gamepanel and check for pause key or runGame to return some value
                if (key.get(KeyEvent.VK_ESCAPE) == true) this.screenState = ScreenStates.PAUSED;
                runGame(key);
                break;
            case PAUSED:
                //Display pause panel depending on input continues to starting screen or home         
                break;
            }
    }
    
    public void runGame(HashMap<Integer, Boolean> key) {
        switch(this.gameState){
            case GENERATION_PHASE:
                //Set tetromino to the piece passed in, sets start location
                //If hold check is true switches hold with current piece
                //Continues to FALLING_PHASE 
                break;
            case FALLING_PHASE:
                //Allow user input to move piece right, left, rotate, and drop; also drops each x seconds based on level
                //If hold is pressed, set hold check true and return to GENERATION_PHASE
                //checks for piece underneath current tetromino and sets state to LOCK_PHASE if true
                //Hard drop skips to CLEAR_PHASE
                break;
            case LOCK_PHASE:
                //Allow user input to move piece right, left, rotate, and lock; force locks after x seconds
                //If hold is pressed, set hold check true and return to GENERATION_PHASE
                //If piece is now able to drop revert to FALLING_PAHSE
                //If locked continue to CLEAR_PHASE
                break;
            case CLEAR_PHASE:
                //Writes current tetromino to its loction on the board and checks for lines
                //Clears lines and tallys them
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
