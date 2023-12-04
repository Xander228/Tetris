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
                colorScale = 40;
                break;
            case 2:
                colorScale = 20;
                break;
            case 3:
                colorScale = -40;
                break;
            case 4:
                colorScale = -20;
                break;
        }
        int r =   color.getRed() + colorScale;
        int g = color.getGreen() + colorScale;
        int b =  color.getBlue() + colorScale;
        return new Color(r, g, b);
    }
    
    public static void drawSquare(int x, int y, Color color, Graphics g) {
        //draw main square
        g.setColor(color);
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_SIZE);
        
        //draw top edge - edge 1
        g.setColor(colorOf(1, color));
        g.fillRect(x, y, Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(color.darker().darker());
        g.fillRect(x, Constants.PIECE_SIZE - Constants.PIECE_EDGE_WIDTH + y,
                    Constants.PIECE_SIZE, Constants.PIECE_EDGE_WIDTH);
        //draw left edge
        g.setColor(color.darker());
        
        //draw right edge
        g.setColor(color.brighter());
    }
}
