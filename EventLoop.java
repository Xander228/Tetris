public class EventLoop
{
    
    private enum GameStates {
        HOME (0),
        PAUSED (1),
        STARTING (2),
        GENERATION_PHASE (3),
        FALLING_PHASE (4),
        LOCK_PHASE (5),
        CLEAR_PHASE(6);
        UPDATE_PHASE(7);
        
        private final int type; 
        TetrominoType(int typeAsInt) {
            this.type = typeAsInt;
        }
        
        public int toInt() {
            return this.type;
        }
    }

    /**
     * Constructor for objects of class GameState
     */
    public EventLoop()
    {
        // initialise instance variables
        x = 0;
    }


}
