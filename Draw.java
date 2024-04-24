import java.awt.*;

public class Draw
{
    public static void header(Graphics g){
        g.setColor(Constants.PRIMARY_COLOR);
        g.fillRect(0, 0, Constants.PIECE_PANEL_WIDTH, Constants.HEADER_HEIGHT);
        for(int i = 0; i <= Constants.HEADER_HEIGHT / 2; i++) {
            g.drawLine(i, Constants.HEADER_HEIGHT, 0, Constants.HEADER_HEIGHT+i);
        }

    }
    
    public static void square(int x, int y, int color, Graphics g) {
        //draw main square
        g.setColor(Constants.COLORS[color][0]);
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE);
        
        //draw top edge - edge 1
        g.setColor(Constants.COLORS[color][1]);
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(Constants.COLORS[color][2]);
        g.fillRect(x, Constants.CELL_SIZE - Constants.CELL_EDGE_WIDTH + y,
                    Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH);
        //draw left edge
        g.setColor(Constants.COLORS[color][3]);
        for(int i = 0; i <= Constants.CELL_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.CELL_SIZE - (2 * i));
        }
        
        //draw right edge
        g.setColor(Constants.COLORS[color][4]);
        for(int i = Constants.CELL_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.CELL_SIZE - i + x - 1, y + i,
                    1, Constants.CELL_SIZE - (2 * i));
        }
    }

    public static void ghostSquare(int x, int y, int color, Graphics g) {
        //draw main square
        g.setColor(Constants.COLORS[color][0].darker().darker());
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE);

        //draw top edge - edge 1
        g.setColor(Constants.COLORS[color][1].darker().darker());
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH);
        //draw bottom edge
        g.setColor(Constants.COLORS[color][2].darker().darker());
        g.fillRect(x, Constants.CELL_SIZE - Constants.CELL_EDGE_WIDTH + y,
                Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH);
        //draw left edge
        g.setColor(Constants.COLORS[color][3].darker().darker());
        for (int i = 0; i <= Constants.CELL_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.CELL_SIZE - (2 * i));
        }

        //draw right edge
        g.setColor(Constants.COLORS[color][4].darker().darker());
        for (int i = Constants.CELL_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.CELL_SIZE - i + x - 1, y + i,
                    1, Constants.CELL_SIZE - (2 * i));
        }
    }
}
