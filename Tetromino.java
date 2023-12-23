import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tetromino {
    private boolean boardRelative;
    private int pieceNum;
    private int pieceRotation;
    private int pieceX;
    private int pieceY;
    private int[] centerOffsets;
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
    //type is a nuumber 0 - 6 that refers to the type of tetromino
    public Tetromino(int type, int x, int y) {
        this.pieceNum = type;
        this.pieceRotation = 0;
        this.pieceX = x;
        this.pieceY = y;
        this.boardRelative = false;
        
        //set the center offset used in pixel relative mode aka boardRelative == false
        switch(type){
            case(2):
                this.centerOffsets = Constants.SQUARE_PIECE_OFFSET;
                break;
                
            case(4):
                this.centerOffsets = Constants.LINE_PIECE_OFFSET;
                break;
                
            default:
                this.centerOffsets = Constants.DEFAULT_PIECE_OFFSET;
                break;
        }
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
        int xOffset = boardRelative ? 1 : centerOffsets[0];
        int yOffset = boardRelative ? 1 : centerOffsets[1];
        for (int Y = 0; Y < 4; Y++) {
            for (int X = 0; X < 4; X++) {
                if (tetrominos[pieceNum][Y][pieceRotation][X] == 0) continue;
                Draw.square(X * Constants.PIECE_SIZE + pieceX - xOffset, 
                            Y * Constants.PIECE_SIZE + pieceY - yOffset, 
                            tetrominos[pieceNum][Y][pieceRotation][X], 
                            g);
            }
        }
    }

}
