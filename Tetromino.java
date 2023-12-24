import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tetromino {
    private int pieceNum;
    private int pieceRotation;
    
    private boolean boardRelative;
    private int boardX;
    private int boardY;
    private int pixelX;
    private int pixelY;
    
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
    public Tetromino(int type, int x, int y, boolean boardRelative) {
        this.pieceNum = type;
        this.pieceRotation = 0;
        this.boardRelative = boardRelative;
        if (boardRelative)  setBoardCoords(x, y);
        else                setPixelCoords(x, y);
        
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
     
    public void setPixelCoords(int x, int y) {
        this.pixelX = x;
        this.pixelY = y;
    }
    
    public void setBoardCoords(int x, int y) {
        this.boardX = x;
        this.boardY = y;
        
        this.pixelX = Constants.PIECE_SIZE * x;
        this.pixelY = Constants.PIECE_SIZE * y;
    }
    
    public void updatePixelCoords() {
        this.pixelX = Constants.PIECE_SIZE * this.boardX;
        this.pixelY = Constants.PIECE_SIZE * this.boardY;
    }
    
    public void tryLeft(int[][] board){
        if(checkOutOfBounds(this.boardX - 1, this.boardY)) return;
        if(checkOverlap(this.boardX - 1, this.boardY, board)) return;
        this.boardX--;
        updatePixelCoords();
    }
    
    public void tryRight(int[][] board){
        if(checkOutOfBounds(this.boardX + 1, this.boardY)) return;
        if(checkOverlap(this.boardX + 1, this.boardY, board)) return;
        this.boardX++;
        updatePixelCoords();
    }
    
    public void tryDrop(int[][] board){
        if(checkOutOfBounds(this.boardX, this.boardY + 1)) return;
        if(checkOverlap(this.boardX, this.boardY + 1, board)) return;
        this.boardY++;
        updatePixelCoords();
    }
    
    
    public void setBoardRelative(boolean boardRelative) {
        this.boardRelative = boardRelative;
    }
    
    public boolean checkOverlap(int x, int y, int[][] board) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (tetrominos[pieceNum][indexY][pieceRotation][indexX] == 0) continue; 
                if (board[indexY + y][indexX + x] != 0) return true;
            }
        }
        return false;
    }
    
    public boolean checkOutOfBounds(int x, int y) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (tetrominos[pieceNum][indexY][pieceRotation][indexX] == 0) continue; 
                if (indexX + x < 0 || indexX + x > Constants.BOARD_COLS - 1 || indexY + y > Constants.BOARD_ROWS - 1) return true;
            }
        }
        return false;
    }
    
    public void draw(Graphics g) {
        int xOffset = boardRelative ? 0 : centerOffsets[0];
        int yOffset = boardRelative ? 0 : centerOffsets[1];
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (tetrominos[pieceNum][indexY][pieceRotation][indexX] == 0) continue;
                Draw.square(indexX * Constants.PIECE_SIZE + pixelX - xOffset, 
                            indexY * Constants.PIECE_SIZE + pixelY - yOffset, 
                            tetrominos[pieceNum][indexY][pieceRotation][indexX], 
                            g);
            }
        }
    }

}
