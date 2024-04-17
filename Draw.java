import javax.swing.*;
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
        for (int i = 0; i <= Constants.PIECE_EDGE_WIDTH; i++) {
            g.fillRect(x + i, y + i, 1, Constants.PIECE_SIZE - (2 * i));
        }

        //draw right edge
        g.setColor(Constants.COLORS[color][4].darker().darker());
        for (int i = Constants.PIECE_EDGE_WIDTH; i >= 0; i--) {
            g.fillRect(Constants.PIECE_SIZE - i + x - 1, y + i,
                    1, Constants.PIECE_SIZE - (2 * i));
        }
    }

    public static void gameOver(JFrame frame){
        JDialog dialog = new JDialog(frame,"GameOver");
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(frame);
        dialog.setUndecorated(true);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.ACCENT_COLOR));
        dialogPanel.setLayout(new BorderLayout(0, 0));

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Constants.PRIMARY_COLOR);
        JLabel text = new JLabel("GAME OVER", JLabel.CENTER);
        text.setFont(new Font("Arial", Font.BOLD, 26));
        text.setForeground(Constants.BACKGROUND_COLOR);
        textPanel.add(text, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR));
        infoPanel.setLayout(new BorderLayout(0, 0));
        infoPanel.setBackground(Constants.BACKGROUND_COLOR);

        JLabel scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        JLabel lineLabel = new JLabel("Lines: 0", JLabel.CENTER);
        JLabel levelLabel = new JLabel("Level: 1", JLabel.CENTER);

        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        lineLabel.setFont(new Font("Arial", Font.BOLD, 16));
        levelLabel.setFont(new Font("Arial", Font.BOLD, 16));

        scoreLabel.setForeground(Constants.PRIMARY_COLOR);
        lineLabel.setForeground(Constants.PRIMARY_COLOR);
        levelLabel.setForeground(Constants.PRIMARY_COLOR);

        infoPanel.add(scoreLabel, BorderLayout.NORTH);
        infoPanel.add(lineLabel, BorderLayout.CENTER);
        infoPanel.add(levelLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createMatteBorder(5, 25, 5, 25, Constants.BACKGROUND_COLOR));
        buttonPanel.setLayout(new BorderLayout(0, 0));
        buttonPanel.setBackground(Constants.BACKGROUND_COLOR);
        JButton restart = new JButton("NewGame");
        JButton end = new JButton("Exit");
        buttonPanel.add(restart, BorderLayout.WEST);
        buttonPanel.add(end, BorderLayout.EAST);

        dialogPanel.add(textPanel, BorderLayout.NORTH);
        dialogPanel.add(infoPanel, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(dialogPanel);
        dialog.setVisible(true);
    }
}
