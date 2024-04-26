import javax.swing.*;
import java.awt.*;

public class HoldPanel extends JPanel {
    Tetromino tetromino; //Declare tetromino variable to hold the current piece in hold
    
    public HoldPanel() {
        setPreferredSize( new Dimension(Constants.PIECE_PANEL_WIDTH, Constants.HOLD_PANEL_HEIGHT)); //Sets the size of the board panel
        setBackground(Constants.BACKGROUND_COLOR); //Sets the background color of the panel
        JLabel pieceLabel = new JLabel("HOLD"); //Creates a new label object with the text "HOLD" to label the panel
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 18)); //Sets the font and size of the label
        pieceLabel.setForeground(Constants.BACKGROUND_COLOR); //Sets the color of the text
        add(pieceLabel); //Adds the label to the panel
    }

    //Takes the piece passed in and puts it in hold, returns the previously held piece
    public Tetromino switchPiece(Tetromino newPiece) {
        Tetromino oldPiece = this.tetromino; //Stores the current tetromino that will be returned at the end
        this.tetromino = newPiece; //Sets the currently held piece to the one passed in
        this.tetromino.setBoardRelative(false); //Sets the coordinate mode for the piece to pixel relative
        this.tetromino.setPixelCoords(Constants.PIECE_PANEL_WIDTH / 2, (int)(Constants.HOLD_PANEL_HEIGHT * 0.55)); //Sets the pixel location relative to the hold panel
        this.repaint(); //Redraw the updated panel
        return oldPiece; //Returns the previously held piece
    }

    //Override the paint component method inherited from JPanel in order to draw the panel and held tetromino
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Runs code inherited from the JPanel class required to render the panel
        Draw.header(g); //Draw the top header of the panel
        if (tetromino == null) return; //Checks if the tetromino exists, if not, exit the method before it tries to draw the piece to avoid a null exception
        tetromino.draw(g); //Draw the currently held piece
    }
}
