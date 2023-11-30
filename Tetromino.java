import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tetromino {
    public Tetromino() {

    }
     
    public void paintComponent(Graphics g) {
        this.drawGrid(g);
    }
    
    public void update() {
    }
    
    public void drawBoard(Graphics g) {

    }
    
    public void drawGrid(Graphics g) {
        g.setColor(new Color((int)0x21477d).brighter().brighter());
        for(int i = 0; i < Constants.BOARD_COLS / 2; i++) {
            g.fillRect(i * Constants.PIECE_SIZE * 2, 0, Constants.PIECE_SIZE, Constants.BOARD_HEIGHT);
        }
    }
    

}
