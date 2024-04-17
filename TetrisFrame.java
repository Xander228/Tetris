import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class TetrisFrame extends JFrame {
    private GamePanel gamePanel;
    private Timer timer;

    
    public TetrisFrame() {
        // Set up the frame properties
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //menuLoop is a panel within the frame that contains all screenPanels
        //menuLoop switches the current panel based on the screenStates
        //gamePanel = new GamePanel();
        //add(gamePanel);
        pack();
        
        //create a hashmap that maps a key to a true false value to represent weather or not its pressed
        HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>();
        HashMap<Integer, Integer> keyTimes = new HashMap<Integer, Integer>();

        //adds each value of keyList to the hashmap
        for(int key : Constants.KEY_LIST) keyPressed.put(key, false);
        for(int key : Constants.KEY_LIST) keyTimes.put(key, 0);

        // Set up KeyListener for user input
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyPressed.replace(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPressed.replace(e.getKeyCode(), false);
            }
        });

        // Set up a Timer for the game loop
        timer = new Timer(Constants.LOOP_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int key : Constants.KEY_LIST) {
                    if(keyPressed.get(key)) keyTimes.replace(key,1 + keyTimes.get(key));
                    else keyTimes.replace(key, 0);
                }

                //pass in the list of currently pressed keys when running the loop
                /* if(!gamePanel.run(keyTimes)) */ gameOver();
            }
        });
        // Set the frame focusable for KeyListener
        setFocusable(true);

        // Start the timer
        timer.start();

        // Set the frame visible
        setVisible(true);
    }
    private void gameOver(){
        Draw.gameOver(this);
        timer.stop();
    }
    public static void main(String[] args) {
        // Run the application on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisFrame();
            }
        });
    }
}