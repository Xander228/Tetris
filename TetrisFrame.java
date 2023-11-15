import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TetrisFrame extends JFrame {
    private TetrisBoard tetrisBoard;
    private ScorePanel scorePanel;

    public TetrisFrame() {
        // Set up the frame properties
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, 
                Constants.FRAME_HEIGHT);

        // Create an instance of the TetrisBoard class
        tetrisBoard = new TetrisBoard();

        // Create an instance of the ScorePanel class
        scorePanel = new ScorePanel();

        // Add the TetrisBoard and ScorePanel to the frame
        add(tetrisBoard);
        add(scorePanel, BorderLayout.EAST);

        // Set up a Timer for the game loop
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the game logic
                tetrisBoard.update();

                // Update the score
                scorePanel.updateScore();

                // Repaint the board
                tetrisBoard.repaint();
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