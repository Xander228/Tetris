import java.awt.*;
import java.awt.event.KeyEvent;

public class Constants
{
    //Defines a name for the action of each key we wish to listen to, allows for easy edit of key binds
    public static final int ROTATE_CW_KEY = KeyEvent.VK_UP; //Key to rotate the piece clockwise
    public static final int ROTATE_CCW_KEY = KeyEvent.VK_CONTROL; //Key to rotate the piece counter-clockwise
    public static final int DROP_KEY = KeyEvent.VK_DOWN; //Key to soft drop the piece
    public static final int LEFT_KEY = KeyEvent.VK_LEFT; //Key to move the piece left
    public static final int RIGHT_KEY = KeyEvent.VK_RIGHT; //Key to move the piece right
    public static final int HARD_DROP_KEY = KeyEvent.VK_SPACE; //Key to hard drop the piece
    public static final int HOLD_KEY = KeyEvent.VK_SHIFT; //Key to hold the piece


    //A list of all keys used to make it easier to initialize the hash maps
    public static final int[] KEY_LIST = {
            ROTATE_CW_KEY,
            ROTATE_CCW_KEY,
            DROP_KEY,
            LEFT_KEY,
            RIGHT_KEY,
            HARD_DROP_KEY,
            HOLD_KEY,
    };

    public static final int LOOP_TIME = 20; //The time it takes, in milliseconds, for each game loop
    public static final int AUTO_MOVE_TIME = 200; //The amount of time it takes, in milliseconds, for the piece to move every loop while a button is held
    public static final int BASE_FALL_TIME = 1000; //The amount of time it takes, in milliseconds, for the piece to fall 1 cell at level 1
    public static final int BASE_DROP_TIME = BASE_FALL_TIME / 20; //The amount of time it takes, in milliseconds, for the piece to fall 1 cell at level 1 when soft dropping
    public static final int CLEAR_TIME = 200; //The amount of time it takes, in milliseconds, to show the identified rows when a line is cleared
    public static final int AUTO_MOVE_LOOPS = AUTO_MOVE_TIME / LOOP_TIME; //The amount of time, in code loops, for the piece to move every loop while a button is held
    public static final int CLEAR_LOOPS = CLEAR_TIME / LOOP_TIME; //The amount of time, in code loops, to show the identified rows when a line is cleared


    public static final int MOVES_BEFORE_LOCK = 5; //The number of moves allowed for a piece that it above an occupied cell, before it is forced to lock
    public static final int MOVES_TIME_LIMIT = 140; //The amount of time, in milliseconds, that is allowed for a piece that it above an occupied cell to move, before it is forced to lock
    public static final int MOVES_LOOP_LIMIT = MOVES_TIME_LIMIT / LOOP_TIME; //The amount of time, code loops, that is allowed for a piece that it above an occupied cell to move, before it is forced to lock

    public static final int BOARD_COLS = 10; //The visible width of the board, in cells
    public static final int BOARD_ROWS = 20; //The visible height of the board, in cells
    public static final int BUFFER_ZONE = 4; //The additional height of the board, in cells, above the visible top of the board
    public static final int TOTAL_BOARD_ROWS = BOARD_ROWS + BUFFER_ZONE; //The total height of the board, in cells
    public static final int QUEUE_SIZE = 3; //The number of pieces visible in the queue before the pieces are brought into play
    
    public static final int CELL_SIZE = 40; //The width, in pixels, of a single cell on the board
    public static final int CELL_EDGE_WIDTH = CELL_SIZE / 7; //The width of the chamfer, in pixels, of a cell on the board
    public static final int BOARD_WIDTH = BOARD_COLS * CELL_SIZE; //The visible width of the board, in pixels
    public static final int BOARD_HEIGHT = BOARD_ROWS * CELL_SIZE; //The visible width of the board, in pixels

    public static final int GAMEOVER_HEIGHT = 200; //The height of the game over dialog, in pixels
    public static final int GAMEOVER_WIDTH = 300; //The width of the game over dialog, in pixels

    public static final int HEADER_HEIGHT = 30; //The height of the piecePanel headers, in pixels
    public static final int PIECE_PANEL_WIDTH = 200; //The width of the piecePanel, in pixels
    public static final int SCORE_PANEL_HEIGHT = 80; //The height of the scorePanel, in pixels
    public static final int HOLD_PANEL_HEIGHT = 5 * CELL_SIZE; //The height of the holdPanel, in pixels, based on the size of a cell
    public static final int QUEUE_PANEL_HEIGHT = BOARD_HEIGHT - SCORE_PANEL_HEIGHT - HOLD_PANEL_HEIGHT; //The height of the queuePanel, in pixels, based on the height of the other panels
    
    public static final  Color BACKGROUND_COLOR = new Color((int)0x212121); //The color used as the background for most panels
    public static final  Color PRIMARY_COLOR = new Color((int)0x555555); //The color used to for headers and text for most panels
    public static final  Color ACCENT_COLOR = new Color((int)0x373737); //The color used to accent the headers and text for some panels
    
    //Color[0] defines empty board colors
    //Color[1 - 7] define piece colors
    //Color[8] defines identify/clear colors

    //Color[x][0] defines the primary color of a cell
    //Color[x][1-4] defines the accent colors for the chamfers on each cell
    public static final  Color[][] COLORS = {
        {new Color((int)0x212121),new Color((int)0x393939),new Color((int)0x070707),new Color((int)0x171717),new Color((int)0x2f2f2f)},
        {new Color((int)0xff0000),new Color((int)0xff8888),new Color((int)0x950000),new Color((int)0xca0000),new Color((int)0xff4040)},
        {new Color((int)0xffc800),new Color((int)0xffe99d),new Color((int)0x955e00),new Color((int)0xca9300),new Color((int)0xffd955)},
        {new Color((int)0xfff200),new Color((int)0xffff9f),new Color((int)0x959500),new Color((int)0xcaca00),new Color((int)0xffff5e)},
        {new Color((int)0x00ff00),new Color((int)0xa6ffa6),new Color((int)0x009500),new Color((int)0x00ca00),new Color((int)0x77ff77)},
        {new Color((int)0x00ffff),new Color((int)0xaeffff),new Color((int)0x009595),new Color((int)0x00caca),new Color((int)0x77ffff)},
        {new Color((int)0x0000ff),new Color((int)0x8a8aff),new Color((int)0x000095),new Color((int)0x0000ca),new Color((int)0x4646ff)},
        {new Color((int)0xff00ff),new Color((int)0xff93ff),new Color((int)0x950095),new Color((int)0xca00ca),new Color((int)0xff5eff)},
        {new Color((int)0xf2f2f2),new Color((int)0xffffff),new Color((int)0x959595),new Color((int)0xcacaca),new Color((int)0xdddddd)},
    };
    
}
