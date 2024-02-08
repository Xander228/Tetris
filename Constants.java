import java.awt.*;
import java.awt.event.KeyEvent;

public class Constants
{
    //creates a list of Keys that we wish to listen to
    public static final int ROTATE_CW_KEY = KeyEvent.VK_UP;
    public static final int ROTATE_CCW_KEY = KeyEvent.VK_CONTROL;
    public static final int DROP_KEY = KeyEvent.VK_DOWN;
    public static final int LEFT_KEY = KeyEvent.VK_LEFT;
    public static final int RIGHT_KEY = KeyEvent.VK_RIGHT;
    public static final int HARD_DROP_KEY = KeyEvent.VK_SPACE;
    public static final int HOLD_KEY = KeyEvent.VK_SHIFT;




    public static final int[] KEY_LIST = {
            ROTATE_CW_KEY,
            ROTATE_CCW_KEY,
            DROP_KEY,
            LEFT_KEY,
            RIGHT_KEY,
            HARD_DROP_KEY,
            HOLD_KEY,
    };

    public static final int LOOP_TIME = 20;
    public static final int AUTO_MOVE_TIME = 200;
    public static final int BASE_FALL_TIME = 1000;
    public static final int CLEAR_TIME = 200;
    public static final int AUTO_MOVE_LOOPS = AUTO_MOVE_TIME / LOOP_TIME;
    public static final int CLEAR_LOOPS = CLEAR_TIME / LOOP_TIME;
    public static final int BASE_DROP_TIME = BASE_FALL_TIME / 20;

    public static final int MOVES_BEFORE_LOCK = 5;
    public static final int MOVES_TIME_LIMIT = 140;
    public static final int MOVES_LOOP_LIMIT = MOVES_TIME_LIMIT / LOOP_TIME;

    public static final int BOARD_COLS = 10;
    public static final int BOARD_ROWS = 20;
    public static final int BUFFER_ZONE = 4;
    public static final int TOTAL_BOARD_ROWS = BOARD_ROWS + BUFFER_ZONE;
    public static final int QUEUE_SIZE = 3;
    
    public static final int PIECE_SIZE = 40;
    public static final int PIECE_EDGE_WIDTH = PIECE_SIZE / 7;
    public static final int BOARD_WIDTH = BOARD_COLS * PIECE_SIZE;
    public static final int BOARD_HEIGHT = BOARD_ROWS * PIECE_SIZE;
    
    public static final int HEADER_HEIGHT = 30;
    public static final int PIECE_PANEL_WIDTH = 200;
    public static final int SCORE_PANEL_HEIGHT = 80;
    public static final int HOLD_PANEL_HEIGHT = 5 * PIECE_SIZE;
    public static final int QUEUE_PANEL_HEIGHT = BOARD_HEIGHT - SCORE_PANEL_HEIGHT - HOLD_PANEL_HEIGHT;
    public static final int FRAME_WIDTH = BOARD_WIDTH + PIECE_PANEL_WIDTH;
    public static final int FRAME_HEIGHT = BOARD_HEIGHT;
    
    public static final  Color BACKGROND_COLOR = new Color((int)0x212121);
    public static final  Color ACCENT_COLOR = new Color((int)0x555555);
    
    //Color[0] defines board colors
    //Color[1 - 7] define piece colors
    //Color[8] defines clear colors
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
