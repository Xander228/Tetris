import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Constants
{
    static int BOARD_COLS = 10;
    static int BOARD_ROWS = 20;
    static int PIECE_SIZE = 40;
    static int PIECE_EDGE_WIDTH = PIECE_SIZE / 7;
    static int BOARD_WIDTH = BOARD_COLS * PIECE_SIZE;
    static int BOARD_HEIGHT = BOARD_ROWS * PIECE_SIZE;
    static int PIECE_PANEL_WIDTH = 200;
    static int SCORE_PANEL_HEIGHT = 50;
    static int FRAME_WIDTH = BOARD_WIDTH + PIECE_PANEL_WIDTH;
    static int FRAME_HEIGHT = BOARD_HEIGHT + SCORE_PANEL_HEIGHT;
    static Color ACCENT_COLOR = new Color((int)0x00ffff);
    static Color[][] COLORS = {
        {new Color((int)0x212121),new Color((int)0x393939),new Color((int)0x070707),new Color((int)0x171717),new Color((int)0x2f2f2f)},
        {new Color((int)0xff0000),new Color((int)0xff8888),new Color((int)0x950000),new Color((int)0xca0000),new Color((int)0xff4040)},
        {new Color((int)0xffc800),new Color((int)0xffe99d),new Color((int)0x955e00),new Color((int)0xca9300),new Color((int)0xffd955)},
        {new Color((int)0xfff200),new Color((int)0xffff9f),new Color((int)0x959500),new Color((int)0xcaca00),new Color((int)0xffff5e)},
        {new Color((int)0x00ff00),new Color((int)0xa6ffa6),new Color((int)0x009500),new Color((int)0x00ca00),new Color((int)0x77ff77)},
        {new Color((int)0x00ffff),new Color((int)0xaeffff),new Color((int)0x009595),new Color((int)0x00caca),new Color((int)0x77ffff)},
        {new Color((int)0x0000ff),new Color((int)0x8a8aff),new Color((int)0x000095),new Color((int)0x0000ca),new Color((int)0x4646ff)},
        {new Color((int)0xff00ff),new Color((int)0xff93ff),new Color((int)0x950095),new Color((int)0xca00ca),new Color((int)0xff5eff)}
    };
    
}
