import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tetromino {
    private boolean boardRelative;
    private int pieceNum;
    private int pieceRotation;
    private int pieceX;
    private int pieceY;
    private static int[][][][] tetrominos = {
        {        
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },
            { {1,1,0,0}, {0,0,1,0}, {0,0,0,0}, {0,1,0,0} },  
            { {0,1,1,0}, {0,1,1,0}, {1,1,0,0}, {1,1,0,0} },  
            { {0,0,0,0}, {0,1,0,0}, {0,1,1,0}, {1,0,0,0} }, 
        }, 
        {       
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },
            { {0,0,2,0}, {0,2,0,0}, {0,0,0,0}, {2,2,0,0} },
            { {2,2,2,0}, {0,2,0,0}, {2,2,2,0}, {0,2,0,0} },
            { {0,0,0,0}, {0,2,2,0}, {2,0,0,0}, {0,2,0,0} },
        },
        {    
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },        
            { {0,3,3,0}, {0,3,3,0}, {0,3,3,0}, {0,3,3,0} }, 
            { {0,3,3,0}, {0,3,3,0}, {0,3,3,0}, {0,3,3,0} },  
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },
        },
        {   
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },      
            { {0,4,4,0}, {0,4,0,0}, {0,0,0,0}, {4,0,0,0} },  
            { {4,4,0,0}, {0,4,4,0}, {0,4,4,0}, {4,4,0,0} }, 
            { {0,0,0,0}, {0,0,4,0}, {4,4,0,0}, {0,4,0,0} }, 
        },
        {    
            { {0,0,0,0}, {0,0,5,0}, {0,0,0,0}, {0,5,0,0} },
            { {5,5,5,5}, {0,0,5,0}, {0,0,0,0}, {0,5,0,0} },
            { {0,0,0,0}, {0,0,5,0}, {5,5,5,5}, {0,5,0,0} },
            { {0,0,0,0}, {0,0,5,0}, {0,0,0,0}, {0,5,0,0} },
        },
        {     
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },
            { {6,0,0,0}, {0,6,6,0}, {0,0,0,0}, {0,6,0,0} },
            { {6,6,6,0}, {0,6,0,0}, {6,6,6,0}, {0,6,0,0} },
            { {0,0,0,0}, {0,6,0,0}, {0,0,6,0}, {6,6,0,0} },
        },
        {         
            { {0,0,0,0}, {0,0,0,0}, {0,0,0,0}, {0,0,0,0} },
            { {0,7,0,0}, {0,7,0,0}, {0,0,0,0}, {0,7,0,0} },  
            { {7,7,7,0}, {0,7,7,0}, {7,7,7,0}, {7,7,0,0} },  
            { {0,0,0,0}, {0,7,0,0}, {0,7,0,0}, {0,7,0,0} },  
        },

    };
    
    public Tetromino(int type, int x, int y) {
        this.pieceNum = type;
        this.pieceRotation = 0;
        this.pieceX = x;
        this.pieceY = y;
        this.boardRelative = false;
    }
     
    public void setCoords(int x, int y) {
        int scalar = boardRelative ? Constants.PIECE_SIZE : 1;
        this.pieceX = scalar * x;
        this.pieceY = scalar * y;
    }
    
    public void setBoardRelative(boolean boardRelative) {
        this.boardRelative = boardRelative;
    }
    
    void draw(Graphics g) {
        for (int Y = 0; Y < 4; Y++) {
            for (int X = 0; X < 4; X++) {
                if (tetrominos[pieceNum][Y][pieceRotation][X] == 0) continue;
                Draw.square(X * Constants.PIECE_SIZE + pieceX, 
                            Y * Constants.PIECE_SIZE + pieceY, 
                            tetrominos[pieceNum][Y][pieceRotation][X], 
                            g);
            }
        }
    }

}
