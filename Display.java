import javax.swing.*;
import java.awt.*;

public class Display
{

    public Display()
    {
        
    }
    
    private static Color colorOf(int side, Color color){
        int colorScale = 0;
        switch(side){
            case 1:
                colorScale = 4 * TetrisBoard.i;
                break;
            case 2:
                colorScale = 2 * TetrisBoard.i;
                break;
            case 3:
                colorScale = -TetrisBoard.i;
                break;
            case 4:
                colorScale = -2 * TetrisBoard.i;
                break;
        }
        int r = Math.min(255, Math.max(0, color.getRed() + colorScale));
        int g = Math.min(255, Math.max(0,color.getGreen() + colorScale));
        int b = Math.min(255, Math.max(0,color.getBlue() + colorScale));
        return new Color(r, g, b);
    }
    
    public static void drawSquare(i
    nt x, int y, Color color, Graphics g) {
        //draw main square
        g.setColor(color);
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_SIZE);
        
        //draw top edge - edge 1
        g.setColor(colorOf(1, color));
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(colorOf(4, color));
        g.fillRect(x, Constants.PIECE_SIZE - Constants.PIECE_EDGE_WIDTH + y,
                    Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw left edge
        g.setColor(colorOf(3, color));
        for(int i = 0; i <= Constants.PIECE_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.PIECE_SIZE - (2 * i));
        }
        
        //draw right edge
        g.setColor(colorOf(2, color));
        for(int i = Constants.PIECE_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.PIECE_SIZE - i + x, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }
}
