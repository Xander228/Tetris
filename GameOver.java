import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOver extends JDialog{
    GameOver(TetrisFrame frame, int score, int lines, int level){
        super(frame,"GameOver"); //Call the parent class's constructor
        this.setSize(Constants.GAMEOVER_WIDTH, Constants.GAMEOVER_HEIGHT); //Sets the size of the dialog
        this.setLocationRelativeTo(frame); //Sets the location of the dialog relative to the frame
        this.setUndecorated(true); //Sets the dialog to undecorated which means no boarder

        JPanel dialogPanel = new JPanel(); //Declare and initialize the dialogPanel that stores the message, buttons, and scores
        dialogPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Constants.ACCENT_COLOR)); //Add a border around the frame
        dialogPanel.setLayout(new BorderLayout(0, 0)); //Sets the edge offset of member panels to properly space them

        JPanel textPanel = new JPanel(); //Declare and initialize the textPanel that stores the game over message
        textPanel.setBackground(Constants.PRIMARY_COLOR); //Sets the background color of the textPanel
        JLabel text = new JLabel("GAME OVER", JLabel.CENTER); //Creates a new label object with the text "GAME OVER" to label the panel
        text.setFont(new Font("Arial", Font.BOLD, 26)); //Sets the font and size of the label
        text.setForeground(Constants.BACKGROUND_COLOR); //Sets the color of the text
        textPanel.add(text, BorderLayout.NORTH); //Adds the text object to the top of textPanel

        JPanel infoPanel = new JPanel(); //Declare and initialize the infoPanel that stores the scores
        infoPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Constants.BACKGROUND_COLOR)); //Add a border around the frame
        infoPanel.setLayout(new BorderLayout(0, 0)); //Sets the edge offset of member panels to properly space them
        infoPanel.setBackground(Constants.BACKGROUND_COLOR); //Sets the background color of the infoPanel

        //Create a subclass of Jlabel made to display the scores
        class ScoreLabel extends JLabel {
            ScoreLabel(String text){
                super(text, JLabel.CENTER); //Call the parent class's constructor
                this.setFont(new Font("Arial", Font.BOLD, 16)); //Sets the font and size of the label
                this.setForeground(Constants.PRIMARY_COLOR); //Sets the color of the text
            }
        }

        infoPanel.add(new ScoreLabel("Score: " + score), BorderLayout.NORTH); //Adds the label that will be used to display the score to the top of the parent panel
        infoPanel.add(new ScoreLabel("Lines: " + lines), BorderLayout.CENTER); //Adds the label that will be used to display the number of lines cleared to the top of the parent panel
        infoPanel.add(new ScoreLabel("Level: " + level), BorderLayout.SOUTH); //Adds the label that will be used to display the level to the top of the parent panel

        JPanel buttonPanel = new JPanel(); //Declare and initialize the buttonPanel that stores the buttons
        buttonPanel.setBorder(BorderFactory.createMatteBorder(5, 25, 5, 25, Constants.BACKGROUND_COLOR)); //Add a border around the frame
        buttonPanel.setLayout(new BorderLayout(0, 0)); //Sets the edge offset of member panels to properly space them
        buttonPanel.setBackground(Constants.BACKGROUND_COLOR); //Sets the background color of the textPanel

        //Create a subclass of JButton made to display the action buttons
        class GameButton extends JButton {
            GameButton(String text){
                super(text); //Call the parent class's constructor
                this.setFocusable(false); //Set focusable to false in order to remove the visible focus outline
                this.setPreferredSize(new Dimension(100, 30)); //Sets the size of the button
                this.setBackground(Constants.PRIMARY_COLOR);  //Sets the background color
                this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Constants.ACCENT_COLOR)); //Add a border around the button
                this.setFont(new Font("Arial", Font.BOLD, 16)); //Sets the font and size of the label in the button
                this.setForeground(Constants.BACKGROUND_COLOR); //Sets the color of the text
            }
        }

        JButton restart = new GameButton("New Game"); //Creates a new button to reset the game
        //Add an actionListener object that runs actionPerformed when it senses the button press and restarts the game
        restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                GameOver.super.dispose(); //Dispose of this dialog
                frame.startNewGame(); //Run the method in TetrisFrame that starts a new game
            }
        });

        JButton end = new GameButton("Exit"); //Creates a new button to exit the game
        //Add an actionListener object that runs actionPerformed when it senses the button press and exits the game
        end.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); //Creates a swing event to close the frame
            }
        });


        buttonPanel.add(restart, BorderLayout.WEST); //Adds the restart button object to the left of the buttonPanel
        buttonPanel.add(end, BorderLayout.EAST); //Adds the exit button object to the right of the buttonPanel

        dialogPanel.add(textPanel, BorderLayout.NORTH); //Adds the textPanel object to the top of the dialogPanel
        dialogPanel.add(infoPanel, BorderLayout.CENTER); //Adds the infoPanel object to the center of the dialogPanel
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH); //Adds the buttonPanel object to the bottom of the dialogPanel
        this.add(dialogPanel); //Adds the dialogPanel to the dialog
        this.setVisible(true); //Sets the dialog to visible
    }

}
