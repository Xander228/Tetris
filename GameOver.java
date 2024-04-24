import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver extends JDialog{
    GameOver(TetrisFrame frame, int score, int lines, int level){
        super(frame,"GameOver");
        this.setSize(Constants.GAMEOVER_WIDTH, Constants.GAMEOVER_HEIGHT);
        this.setLocationRelativeTo(frame);
        this.setUndecorated(true);

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

        class ScoreLabel extends JLabel {
            ScoreLabel(String text){
                super(text, JLabel.CENTER);
                this.setFont(new Font("Arial", Font.BOLD, 16));
                this.setForeground(Constants.PRIMARY_COLOR);
            }
        }

        infoPanel.add(new ScoreLabel("Score: " + score), BorderLayout.NORTH);
        infoPanel.add(new ScoreLabel("Lines: " + lines), BorderLayout.CENTER);
        infoPanel.add(new ScoreLabel("Level: " + level), BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createMatteBorder(5, 25, 5, 25, Constants.BACKGROUND_COLOR));
        buttonPanel.setLayout(new BorderLayout(0, 0));
        buttonPanel.setBackground(Constants.BACKGROUND_COLOR);

        class GameButton extends JButton {
            GameButton(String text){
                super(text);
                this.setFocusable(false);
                this.setPreferredSize(new Dimension(100, 30));
                this.setBackground(Constants.PRIMARY_COLOR);
                this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.ACCENT_COLOR));
                this.setFont(new Font("Arial", Font.BOLD, 16));
                this.setForeground(Constants.BACKGROUND_COLOR);
            }
        }

        JButton restart = new GameButton("New Game");

        restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GameOver.super.dispose();
                frame.startNewGame();
            }
        });

        JButton end = new GameButton("Exit");
        end.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });


        buttonPanel.add(restart, BorderLayout.WEST);
        buttonPanel.add(end, BorderLayout.EAST);

        dialogPanel.add(textPanel, BorderLayout.NORTH);
        dialogPanel.add(infoPanel, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(dialogPanel);
        this.setVisible(true);
    }

}
