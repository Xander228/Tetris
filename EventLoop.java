import java.util.HashMap;

public class EventLoop
{
    
    private enum GameStates {
        HOME (0),
        PAUSED (1),
        STARTING (2),
        GENERATION_PHASE (3),
        FALLING_PHASE (4),
        LOCK_PHASE (5),
        CLEAR_PHASE (6),
        UPDATE_PHASE (7);
        
        private final int type; 
        GameStates(int typeAsInt) {
            this.type = typeAsInt;
        }
        
        public int toInt() {
            return this.type;
        }
    }

    private GameStates gameState;
    
    public EventLoop()
    {
        this.gameState = GameStates.HOME;
        
    }
    
    public void run(HashMap<Integer, Boolean> pressedKeys) {
        switch(this.gameState){
            case HOME:
                //Display home panel
                break;
            case PAUSED:
                //Display pause panel
                break;
            case STARTING:
                //Display starting panel and then Mainpanel              
                break;
            case GENERATION_PHASE:
                //Set tetromino to the piece passed in, sets start location, index queue and reshuffle bag if necessary
                break;
            case FALLING_PHASE:
                //Allow user input to move piece right, left, rotate, and drop; also drops each x seconds based on level
                //checks for piece underneath current tetromino and sets state to LOCK_PHASE if true
                //Hard drop skips to clear phase
                break;
            case LOCK_PHASE:
                //Allow user input to move piece right, left, rotate, and lock; force locks after x seconds
                //If piece is now able to drop revert to FALLING_PAHSE
                //If locked continue to CLEAR_PHASE
                break;
            case CLEAR_PHASE:
                //Writes current tetromino to its loction on the board and checks
                break;
            case UPDATE_PHASE:
                
                break;
        }
    }


}
