import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MenuLoop extends JPanel {
    
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
    


    private ScreenStates screenState;
    private GameLoop gameLoop;
    
    public MenuLoop()
    {
        this.screenState = ScreenStates.RUNNING;
        gameLoop = new GameLoop();
        add(gameLoop);
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
                if (key.get(KeyEvent.VK_ESCAPE)) this.screenState = ScreenStates.PAUSED;
                gameLoop.run(key);
                break;
            case PAUSED:
                //Display pause panel depending on input continues to starting screen or home         
                break;
            }
    }
}
