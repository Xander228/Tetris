import javax.swing.*;
import java.awt.*;

public class Square
{

    public Square()
    {
        
    }
    
    private static Color colorOf(int side, Color color){
        int colorScale = 0;
        switch(side){
            case 1:
                colorScale = 4 * TetrisBoard.i;
                break;
            case 2:
                colorScale = 3 * TetrisBoard.i;
                break;
            case 3:
                colorScale = -TetrisBoard.i;
                break;
            case 4:
                colorScale = -2 * TetrisBoard.i;
                break;
        }
        return Color.BLACK;
    }
    
    public static void draw(int x, int y, int color, Graphics g) {
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
            g.fillRect(Constants.PIECE_SIZE - i + x, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }
}
