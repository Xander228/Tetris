import java.awt.*;

public class Draw
{
    //Draws a header for the hold and queue panels
    public static void header(Graphics g){
        g.setColor(Constants.PRIMARY_COLOR); //Sets the "pen" color
        g.fillRect(0, 0, Constants.PIECE_PANEL_WIDTH, Constants.HEADER_HEIGHT); //Draw the top part of the header
        //Draw the left chamfer
        for(int i = 0; i <= Constants.HEADER_HEIGHT / 2; i++) {
            g.drawLine(i, Constants.HEADER_HEIGHT, 0, Constants.HEADER_HEIGHT+i);
        }

    }

    //Draw a normal cell based on position and color passes in
    public static void square(int x, int y, int color, Graphics g) {
        g.setColor(Constants.COLORS[color][0]); //Select main cell color - color 0
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE); //Draw main square
        

        g.setColor(Constants.COLORS[color][1]); //Select top edge accent color - color 1
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH);

        g.setColor(Constants.COLORS[color][2]); //Select bottom edge accent color - color 2
        g.fillRect(x, Constants.CELL_SIZE - Constants.CELL_EDGE_WIDTH + y, Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH); //Draw bottom edge - edge 2

        g.setColor(Constants.COLORS[color][3]); //Select left edge accent color - color 3
        //Draw left edge with chamfers - edge 3
        for(int i = 0; i <= Constants.CELL_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.CELL_SIZE - (2 * i));
        }

        g.setColor(Constants.COLORS[color][4]); //Select right edge accent color - color 4
        //Draw right edge with chamfers - edge 4
        for(int i = Constants.CELL_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.CELL_SIZE - i + x - 1, y + i,
                    1, Constants.CELL_SIZE - (2 * i));
        }
    }

    public static void ghostSquare(int x, int y, int color, Graphics g) {
        g.setColor(Constants.COLORS[color][0].darker().darker()); //Select main cell color but two shades darker - color 0
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE); //Draw main square

        g.setColor(Constants.COLORS[color][1].darker().darker()); //Select top edge accent color but two shades darker - color 1
        g.fillRect(x, y, Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH); //Draw top edge - edge 1

        g.setColor(Constants.COLORS[color][2].darker().darker()); //Select bottom edge accent color but two shades darker - color 2
        g.fillRect(x, Constants.CELL_SIZE - Constants.CELL_EDGE_WIDTH + y, Constants.CELL_SIZE, Constants.CELL_EDGE_WIDTH); //Draw bottom edge - edge 2

        g.setColor(Constants.COLORS[color][3].darker().darker()); //Select left edge accent color but two shades darker - color 3
        //Draw left edge with chamfers - edge 3
        for (int i = 0; i <= Constants.CELL_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.CELL_SIZE - (2 * i));
        }

        g.setColor(Constants.COLORS[color][4].darker().darker()); //Select right edge accent color but two shades darker - color 4
        //Draw right edge with chamfers - edge 4
        for (int i = Constants.CELL_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.CELL_SIZE - i + x - 1, y + i,
                    1, Constants.CELL_SIZE - (2 * i));
        }
    }
}
