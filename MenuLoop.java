import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import static javax.swing.BorderFactory.createMatteBorder;

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
    private GamePanel gamePanel;
    
    public MenuLoop()
    {
        this.screenState = ScreenStates.RUNNING;
        setLayout(new BorderLayout(0, 0));
        gamePanel = new GamePanel();
        add(gamePanel);
    }
    
    public void run(HashMap<Integer, Integer> keyTimes) {
        switch(this.screenState){
            case HOME:
                //Display home panel with a play and quit button
                break;
            case STARTING:
                //Display starting panel and then Sets state to Running 
                break;
            case RUNNING:
                //Display gamePanel and check for pause key or runGame to return some value
                if (keyTimes.get(KeyEvent.VK_ESCAPE) >= 1) this.screenState = ScreenStates.PAUSED;
                gamePanel.runGameLoop(keyTimes);
                break;
            case PAUSED:
                //Display pause panel depending on input continues to starting screen or home         
                break;
            }
    }
}
