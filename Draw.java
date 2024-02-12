import javax.swing.*;
import java.awt.*;

public class Draw
{
    public static void header(Graphics g){
        g.setColor(Constants.ACCENT_COLOR);
        g.fillRect(0, 0, Constants.PIECE_PANEL_WIDTH, Constants.HEADER_HEIGHT);
        for(int i = 0; i <= Constants.HEADER_HEIGHT / 2; i++) {
            g.drawLine(i, Constants.HEADER_HEIGHT, 0, Constants.HEADER_HEIGHT+i);
        }

    }
    
    public static void square(int x, int y, int color, Graphics g) {
        //draw main square
        g.setColor(Constants.COLORS[color][0]);
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_SIZE);
        
        //draw top edge - edge 1
        g.setColor(Constants.COLORS[color][1]);
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(Constants.COLORS[color][2]);
        g.fillRect(x, Constants.PIECE_SIZE - Constants.PIECE_EDGE_WIDTH + y,
                    Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw left edge
        g.setColor(Constants.COLORS[color][3]);
        for(int i = 0; i <= Constants.PIECE_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.PIECE_SIZE - (2 * i));
        }
        
        //draw right edge
        g.setColor(Constants.COLORS[color][4]);
        for(int i = Constants.PIECE_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.PIECE_SIZE - i + x - 1, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }

    public static void ghostSquare(int x, int y, int color, Graphics g) {
        //draw main square
        g.setColor(Constants.COLORS[color][0].darker().darker());
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_SIZE);
        
        //draw top edge - edge 1
        g.setColor(Constants.COLORS[color][1].darker().darker());
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(Constants.COLORS[color][2].darker().darker());
        g.fillRect(x, Constants.PIECE_SIZE - Constants.PIECE_EDGE_WIDTH + y,
                    Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw left edge
        g.setColor(Constants.COLORS[color][3].darker().darker());
        for(int i = 0; i <= Constants.PIECE_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.PIECE_SIZE - (2 * i));
        }
        
        //draw right edge
        g.setColor(Constants.COLORS[color][4].darker().darker());
        for(int i = Constants.PIECE_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.PIECE_SIZE - i + x - 1, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }

    public static void gameOver( int score, int lines, int level, Graphics g){
        int x = (Constants.BOARD_WIDTH / 2) - (Constants.GAMEOVER_WIDTH / 2);
        int y = (Constants.BOARD_HEIGHT / 2) - (Constants.GAMEOVER_HEIGHT / 2);
        g.setColor(Constants.ACCENT_COLOR);
        g.fillRect(x, y, Constants.GAMEOVER_WIDTH, Constants.GAMEOVER_HEIGHT);
        for(int i = 0; i <= Constants.HEADER_HEIGHT / 2; i++) {
            g.drawLine(i + x, Constants.HEADER_HEIGHT + y, x, Constants.HEADER_HEIGHT + i + y);
        }

    }
}
