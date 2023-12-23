import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisFrame extends JFrame {
    private JPanel mainPanel;
    private TetrisBoard tetrisBoard;
    private PiecePanel piecePanel;
    static int i = 0;
    
    public TetrisFrame() {
        // Set up the frame properties
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        //Create a panel within the fram that contains all subpanels
        //This is done to add a border around and between the subpanels
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.ACCENT_COLOR));
        mainPanel.setBackground(Constants.ACCENT_COLOR);
        mainPanel.setLayout(new BorderLayout(10, 10));
        
        // Create an instance of the TetrisBoard class
        tetrisBoard = new TetrisBoard();

        // Create an instance of the ScorePanel class
        piecePanel = new PiecePanel();
        
        
        // Add the TetrisBoard and ScorePanel to the frame
        
        mainPanel.add(tetrisBoard);
        mainPanel.add(piecePanel, BorderLayout.EAST);
        
        add(mainPanel);
        pack();
        
        // Set up a Timer for the game loop
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i >= 7) i = 0;
                
                // Update the game logic
                tetrisBoard.update();

                // Update piece panels
                piecePanel.update();

            }
        });

        // Set up KeyListener for user input
        tetrisBoard.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                // Handle key presses for game controls (e.g., move left, move right, rotate)
                tetrisBoard.handleKeyPress(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        // Set the frame focusable for KeyListener
        tetrisBoard.setFocusable(true);

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