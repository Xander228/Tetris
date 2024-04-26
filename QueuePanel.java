import javax.swing.*;
import java.awt.*;

public class QueuePanel extends JPanel {
    private PieceBag pieceBag; //Declare pieceBag to store bag of pieces
    private Tetromino[] currentQueue; //Declare a tetromino array to store the pieces in queue
    
    public QueuePanel() {
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, Constants.QUEUE_PANEL_HEIGHT)); //Sets the size of the board panel
        setBackground(Constants.BACKGROUND_COLOR); //Sets the background color of the panel
        JLabel pieceLabel = new JLabel("QUEUE"); //Creates a new label object with the text "QUEUE" to label the panel
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 18)); //Sets the font and size of the label
        pieceLabel.setForeground(Constants.BACKGROUND_COLOR); //Sets the color of the text
        add(pieceLabel); //Adds the label to the panel
        
        pieceBag = new PieceBag(); //Create the PieceBag object
        initializeQueue(); //Initializes the queue
        
    }

    //Initializes the queue by pre-filling the 3 pieces in queue
    private void initializeQueue() {
        currentQueue = new Tetromino[Constants.QUEUE_SIZE]; //Initializes the currentQueue as a tetromino array with Constants.QUEUE_SIZE indexes
        //Creates a new piece in the queue for every index based off of the number pulled from the piece bag
        for (int i = 0; i < currentQueue.length; i++)
            currentQueue[i] = new Tetromino(pieceBag.pullNewPiece(), 0, 0, false); //Create a piece with a type from pieceBag
    }

    //Pulls a piece from queue, indexes the queue down, and refills the last index
    public Tetromino pullFromQueue() {
        Tetromino pulledPiece = currentQueue[0]; //Pulls the first piece in the queue
        for (int i = 0; i < currentQueue.length - 1; i++) currentQueue[i] = currentQueue[i + 1]; //Shifts every piece down one in the queue
        currentQueue[currentQueue.length - 1] = new Tetromino(pieceBag.pullNewPiece(), 0, 0, false); //Pulls a new piece from the bag to store in the last slot of the queue
        repaint(); //Redraw the updated panel
        return pulledPiece; //Return piece originally pulled from the queue
    }

    //Override the paint component method inherited from JPanel in order to draw the panel and tetrominos held in queue
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Runs code inherited from the JPanel class required to render the panel
        Draw.header(g); //Draw the top header of the panel
        Tetromino tetromino; //Creates a tetromino variable to store the current tetromino being drawn
        //Indexes through the currentQueue array and draws each piece
        for (int i = 0; i < Constants.QUEUE_SIZE; i++) {
            tetromino = currentQueue[i];  //Get the tetromino in the queue
            tetromino.setPixelCoords(Constants.PIECE_PANEL_WIDTH / 2, (int) (Constants.HOLD_PANEL_HEIGHT * 0.55) + i * 4 * Constants.CELL_SIZE); //Set the coordinates of that piece based on its place in queue
            tetromino.draw(g); //Draw the tetromino
        }
    }
}
