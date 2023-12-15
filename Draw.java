import javax.swing.*;
import java.awt.*;

public class Draw
{
        private static Color colorOf(int side, Color color){
        float colorScale = 0;
        float[] colorInHSB = new float[3];
        switch(side){
            case 1:
                colorScale = 1.7f;
                break;
            case 2:
                colorScale = 1.5f;
                break;
            case 3:
                colorScale = -.5f;
                break;
            case 4:
                colorScale = -1f;
                break;
        }
        
        Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),colorInHSB);
        return Color.getHSBColor( colorInHSB[0],
                            Math.min(1, Math.max(0,colorInHSB[1] - .4f * colorScale)),
                            Math.min(1, Math.max(0,colorInHSB[2] + .4f * colorScale)));
    }
    
    public static void square(int x, int y, int color, Graphics g) {
        x = x * Constants.PIECE_SIZE;
        y = y * Constants.PIECE_SIZE;
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
    
    public static void Square(int x, int y, Color color, Graphics g) {
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
