import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;

public class TetrisFrame extends JFrame {
    private MenuLoop menuLoop;

    
    public TetrisFrame() {
        // Set up the frame properties
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //menuLoop is a panel within the frame that contains all screenPanels
        //menuLoop switches the current panel based on the screenStates
        menuLoop = new MenuLoop();
        add(menuLoop);
        pack();
        

        //create a hashmap that maps a key to a true false value to represent weather or not its pressed
        HashMap<Integer, Boolean> pressedKeys = new HashMap<Integer, Boolean>();
        pressedKeys.put(KeyEvent.VK_UP, false);
        pressedKeys.put(KeyEvent.VK_DOWN, false);
        pressedKeys.put(KeyEvent.VK_LEFT, false);
        pressedKeys.put(KeyEvent.VK_RIGHT, false);
        pressedKeys.put(KeyEvent.VK_ESCAPE, false);
        
        // Set up KeyListener for user input
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                // Handle key presses for game controls (e.g., move left, move right, rotate)
                pressedKeys.replace(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.replace(e.getKeyCode(), false);
            }
        });

        // Set up a Timer for the game loop
        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pass in the list of currently pressed keys when running the loop
                menuLoop.run(pressedKeys);
            }
        });
        // Set the frame focusable for KeyListener
        setFocusable(true);

        // Start the timer
        timer.start();

        // Set the frame visible
        setVisible(true);
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