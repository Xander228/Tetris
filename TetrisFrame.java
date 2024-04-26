import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class TetrisFrame extends JFrame {
    private GamePanel gamePanel; //Declare the gamePanel that stores the entire game

    private Timer timer; //Declare to timer that periodically runs the program

    public TetrisFrame() {
        //Set up the frame properties
        setTitle("Tetris"); //Title of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Behavior on close, exits the program when the frame is closed
        setResizable(false); //sets the frame to a fixed size, not resizeable by a user

        //GamePanel is a panel within the frame that contains sub panels related to gameplay
        gamePanel = new GamePanel(); //Create new gamePanel object
        add(gamePanel); //Adds the gamePanel object to the frame
        pack(); //Resizes the frame to fit the gamePanel
        
        //Create a hashmap that maps a key to a true false value to represent weather or not its pressed
        HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>(); //Map of currently pressed keys
        HashMap<Integer, Integer> keyTimes = new HashMap<Integer, Integer>(); //Map of the amount of time, in code loops, that a key has been pressed

        //Adds each key, with its default value, of keyList to the hashmaps
        for(int key : Constants.KEY_LIST) keyPressed.put(key, false); //Creates a key for each index in KEY_LIST
        for(int key : Constants.KEY_LIST) keyTimes.put(key, 0); //Creates a key for each index in KEY_LIST

        //Set up KeyListener to accept user input though key presses
        //Adds the KeyListener defined below to the frame
        addKeyListener(
            //creates a new KeyListener object with the following methods overriden
            new KeyListener() {
                //Must be overriden to create object due to KeyListener being an interface
                @Override
                public void keyTyped(KeyEvent e) {
                }

                //Overrides the keyPressed method to add the state of the current pressed key to the map
                @Override
                public void keyPressed(KeyEvent e) {
                    keyPressed.replace(e.getKeyCode(), true); //receives the pressed key and replaces the existing value in the map with true

                }

                //Overrides the keyReleased method to add the state of the current released key to the map
                @Override
                public void keyReleased(KeyEvent e) {
                    keyPressed.replace(e.getKeyCode(), false); //receives the released key and replaces the existing value in the map with false
                }
            }
        );

        //Set up a Timer to run the game loop
        //Creates a new timer object with a delay equal to LOOP_TIME which is the time it takes for one loop
        timer = new Timer(Constants.LOOP_TIME,
            //Creates a new ActionListener object with the following methods overriden
            new ActionListener() {
                //Overrides the actionPerformed method which is called every time the LOOP_TIME elapses
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Every loop, key times are incremented as long as the key is still pressed
                    for(int key : Constants.KEY_LIST) {
                        if(keyPressed.get(key)) keyTimes.replace(key,1 + keyTimes.get(key)); //If the key is still being held, increase the time
                        else keyTimes.replace(key, 0); //If the key has been released then reset the timer to 0
                    }

                    //Runs the gamePanel's run method and passes in the map of key times
                    //If the gamePanel fails to return true, it means the game is over and the gameOver method is called
                    if(!gamePanel.run(keyTimes)) gameOver();
                }
            }
        );
        //Set the frame focusable for KeyListener, allowing it to accept key inputs when in focus
        setFocusable(true);

        //Start the timer
        timer.start();

        //Set the frame visible
        setVisible(true);
    }

    //Gets the scores and creates a game over object to display the GameOverPanel, then stops the game timer
    private void gameOver(){
        int[] scores = gamePanel.getScores(); //Gets an array of the scores from the gamePanel
        new GameOver(this, scores[0], scores[1], scores[2]); //Creates the GameOver object which displays the scores and end options
        timer.stop(); //Stops the game timer to stop calls to this method
    }

    //Replaces old gamePanel with a new instance, then restarts the timer
    public void startNewGame(){
        gamePanel = new GamePanel(); //Replaces the existing gamePanel object with the new one
        this.add(gamePanel); //Re-adds the new gamePanel object to the frame
        pack(); //Ensures that the new gamePanel is at its desired size and removes old panel
        timer.start(); //Restarts the game loop
    }

    //Main method
    public static void main(String[] args) {
        new TetrisFrame(); //Create new TetrisFrame object
    }
}