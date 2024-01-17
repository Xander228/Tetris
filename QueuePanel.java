import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QueuePanel extends JPanel {
    private JLabel pieceLabel;
    private PieceBag pieceBag;
    private Tetromino[] currentQueue;
    
    public QueuePanel() {
        // Initialize components, set layout, etc.
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, 
                                        Constants.QUEUE_PANEL_HEIGHT));
        setBackground(Constants.COLORS[0][0]);
        pieceLabel = new JLabel("QUEUE");
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(pieceLabel);
        
        pieceBag = new PieceBag();
        initializeQueue();
        
    }
    
    private void initializeQueue() {
        currentQueue = new Tetromino[Constants.QUEUE_SIZE];
        for (int i = 0; i < currentQueue.length; i++) currentQueue[i] = new Tetromino(pieceBag.pullNewPiece(), 0, 0, false);
    }
    
    public Tetromino pullFromQueue() {
        //pulls the first piece in the queue
        Tetromino pulledPiece = currentQueue[0];
        //shifts every piece down one in the queue
        for (int i = 0; i < currentQueue.length - 1; i++) currentQueue[i] = currentQueue[i + 1];
        //pulls a new piece from the bag to store in the last slot of the queue
        currentQueue[currentQueue.length - 1] = new Tetromino(pieceBag.pullNewPiece(), 0, 0, false);
        //return the first piece pulled from the queue
        return pulledPiece;
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        //runs code inherited from the parent class required to render the panel
        super.paintComponent(g); 
        //draws the header section
        Draw.header(g);
        //creates a temporary reference variable for the current tetromino being drawn
        Tetromino tetromino;
        //indexes through the currentQueue array and draws each piece
        for(int i = 0; i < 3; i++) {
            //get the tetromino as a given position in the queue
            tetromino = currentQueue[i];
            //set the coordinates of that piece based on its place in queue
            tetromino.setPixelCoords(Constants.PIECE_PANEL_WIDTH / 2, (int)(Constants.HOLD_PANEL_HEIGHT * 0.55) + i * 4 * Constants.PIECE_SIZE);
            //draw the tetromino
            tetromino.draw(g);
        }
        
    }
    
    public void update() {
        repaint();
    }
}
