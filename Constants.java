import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Constants
{
    static int BOARD_COLS = 10;
    static int BOARD_ROWS = 20;
    static int PIECE_SIZE = 40;
    static int BOARD_WIDTH = BOARD_COLS * PIECE_SIZE;
    static int BOARD_HEIGHT = BOARD_ROWS * PIECE_SIZE;
    static int PIECE_PANEL_WIDTH = 200;
    static int SCORE_PANEL_HEIGHT = 50;
    static int FRAME_WIDTH = BOARD_WIDTH + PIECE_PANEL_WIDTH;
    static int FRAME_HEIGHT = BOARD_HEIGHT + SCORE_PANEL_HEIGHT;
    
    static Border BLACK_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);


}
