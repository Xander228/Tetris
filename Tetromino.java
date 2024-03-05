import java.awt.*;

public class Tetromino {
    
    private enum TetrominoType {
        Z (0),
        L (1),
        O (2),
        S (3),
        I (4),
        J (5),
        T (6);
        
        private final int type; 
        TetrominoType(int typeAsInt) {
            this.type = typeAsInt;
        }
        
        public int toInt() {
            return this.type;
        }
    }
    
    //Tetromino bitmaps are formatted as tetrominos[piece number][y index][piece rotation][x index]
    private static final int[][][][] tetrominos = {
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
    
    //Tetromino center offsets formatted in cols x rows (x,y)
    public static final  int[] DEFAULT_PIECE_OFFSET = {(int)(1.5 * Constants.PIECE_SIZE), 2 * Constants.PIECE_SIZE};
    public static final  int[] SQUARE_PIECE_OFFSET = {2 * Constants.PIECE_SIZE, 2 * Constants.PIECE_SIZE};
    public static final  int[] LINE_PIECE_OFFSET = {2 * Constants.PIECE_SIZE, (int)(1.5 * Constants.PIECE_SIZE)};
    
    //Kick test offsets formatted as kickTests[rotation type][test num][xy]
    //The rotation type is an integer from 0 to 3 that refers to the set of tests used on a group rotations
    //A rotation can be written as a>>b where a and b are integers from 0 to 3 and are associated with a given pieceRotation
    
    //Rotations: 0>>1 RotationType: 0
    //Rotations: 1>>0 RotationType: 1
    //Rotations: 1>>2 RotationType: 2
    //Rotations: 2>>1 RotationType: 3
    //Rotations: 2>>3 RotationType: 4
    //Rotations: 3>>2 RotationType: 5
    //Rotations: 3>>0 RotationType: 6
    //Rotations: 0>>3 RotationType: 7
    public static final  int[][][] DEFAULT_KICK_TESTS = {
        {{0, 0}, {-1, 0}, {-1, 1}, { 0,-2}, {-1,-2}},
        {{0, 0}, { 1, 0}, { 1,-1}, { 0, 2}, { 1, 2}},
        {{0, 0}, { 1, 0}, { 1,-1}, { 0, 2}, { 1, 2}},
        {{0, 0}, {-1, 0}, {-1, 1}, { 0,-2}, {-1,-2}},
        {{0, 0}, { 1, 0}, { 1, 1}, { 0,-2}, { 1,-2}},
        {{0, 0}, {-1, 0}, {-1,-1}, { 0, 2}, {-1, 2}},
        {{0, 0}, {-1, 0}, {-1,-1}, { 0, 2}, {-1, 2}},
        {{0, 0}, { 1, 0}, { 1, 1}, { 0,-2}, { 1,-2}}
    };
        
    public static final  int[][][] LINE_KICK_TESTS = {
        {{0, 0}, {-2, 0}, { 1, 0}, {-2,-1}, { 1, 2}},
        {{0, 0}, { 2, 0}, {-1, 0}, { 2, 1}, {-1,-2}},
        {{0, 0}, {-1, 0}, { 2, 0}, {-1, 2}, { 2,-1}},
        {{0, 0}, { 1, 0}, {-2, 0}, { 1,-2}, {-2, 1}},
        {{0, 0}, { 2, 0}, {-1, 0}, { 2, 1}, {-1,-2}},
        {{0, 0}, {-2, 0}, { 1, 0}, {-2,-1}, { 1, 2}},
        {{0, 0}, { 1, 0}, {-2, 0}, { 1,-2}, {-2, 1}},
        {{0, 0}, {-1, 0}, { 2, 0}, {-1, 2}, { 2,-1}}
    };
    
    private TetrominoType type;
    private int pieceRotation;
    
    private boolean boardRelative;
    private int boardX, boardY;
    private int pixelX, pixelY;

    private int lowestLock;
    
    private int[] centerOffsets;
    private int[][][] kickTests;
    private boolean visible;
    
    //type is a number 0 - 6 that refers to the type of tetromino
    public Tetromino(int type, int x, int y, boolean boardRelative) {
        //sets this.type to the value at index type in the array of TetrominoType values
        this.type = TetrominoType.values()[type];
        this.pieceRotation = 0;
        this.boardRelative = boardRelative;
        if (boardRelative)  setBoardCoords(x, y);
        else                setPixelCoords(x, y);

        lowestLock = 0;
        visible = true;

        //set the center offset used in pixel relative mode aka boardRelative == false
        switch(this.type){
            default:
                this.centerOffsets = DEFAULT_PIECE_OFFSET;
                this.kickTests = DEFAULT_KICK_TESTS;
                break;

            case O:
                this.centerOffsets = SQUARE_PIECE_OFFSET;
                break;

            case I:
                this.centerOffsets = LINE_PIECE_OFFSET;
                this.kickTests = LINE_KICK_TESTS;
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

    public void setLowestLock(){
        lowestLock = Math.min(boardY, lowestLock);
    }

    public boolean canResetCounter() {
        return lowestLock > boardY;
    }

    public void lock(int[][] board) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                int cell = tetrominos[this.type.toInt()][indexY][this.pieceRotation][indexX];
                if (cell == 0) continue;
                board[boardX + indexX][boardY + indexY + Constants.BUFFER_ZONE] = cell;
            }
        }
    }
    //returns true if move is successful
    public boolean tryLeft(int[][] board){
        if(isOutOfBounds(this.boardX - 1, this.boardY)) return false;
        if(isOverlapped(this.boardX - 1, this.boardY, board)) return false;
        this.boardX--;
        updatePixelCoords();
        return true;
    }
    
    //returns true if move is successful
    public boolean tryRight(int[][] board){
        if(isOutOfBounds(this.boardX + 1, this.boardY)) return false;
        if(isOverlapped(this.boardX + 1, this.boardY, board)) return false;
        this.boardX++;
        updatePixelCoords();
        return true;
    }

    public boolean tryRotatingCW(int[][] board){
        return tryRotation(false,board);
    }

    public boolean tryRotatingCCW(int[][] board){
        return tryRotation(true,board);
    }






    //returns true if move is successful
    public boolean tryRotation(boolean isCCW, int[][] board){
        //O pieces can't be rotated and thus can't move rotationally
        if(this.type.equals(TetrominoType.O)) return false;
        //If the desired rotation would exceed 3 it finds the next rotation which would be 0
        int desiredRotation = (pieceRotation + (isCCW ? -1 : 1) + 4) % 4;

        int[][] currentTestSet = switch (pieceRotation + ">>" + desiredRotation) {
            case "0>>1" -> kickTests[0];
            case "1>>0" -> kickTests[1];
            case "1>>2" -> kickTests[2];
            case "2>>1" -> kickTests[3];
            case "2>>3" -> kickTests[4];
            case "3>>2" -> kickTests[5];
            case "3>>0" -> kickTests[6];
            case "0>>3" -> kickTests[7];
            default -> null;
        };

        //Rotation change is converted into a String representation used to set the test set

        //if a test case fails, it will continue testing until it runs out of cases, no changes are made
        //if a test case succeeds it will set the pieceRotation to the desiredRotation and apply xy offsets

        for (int[] currentTest : currentTestSet){
            int xOffset = currentTest[0];
            int yOffset = currentTest[1];
            
            if(isOutOfBounds(this.boardX + xOffset, this.boardY - yOffset, desiredRotation)) continue;
            if(isOverlapped(this.boardX + xOffset, this.boardY - yOffset, desiredRotation, board)) continue;
            
            this.pieceRotation = desiredRotation;
            this.boardX += xOffset;
            this.boardY -= yOffset;
            updatePixelCoords();
            return true;
        }
        
        //returns false if all test cases fail
        return false;
    }
    
    //returns true if move is successful
    public boolean tryDrop(int[][] board){
        if(isOutOfBounds(this.boardX, this.boardY + 1)) return false;
        if(isOverlapped(this.boardX, this.boardY + 1, board)) return false;
        this.boardY++;
        updatePixelCoords();
        return true;
    }
    
    public void hardDrop(int[][] board){
        while (tryDrop(board));
    }
    
    public void setBoardRelative(boolean boardRelative) {
        this.boardRelative = boardRelative;
        pieceRotation = 0;
    }
    
    private boolean isOverlapped(int x, int y, int rotation, int[][] board) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                //ignores tile if no mino occupies it
                if (tetrominos[this.type.toInt()][indexY][rotation][indexX] == 0) continue; 
                
                //if a mino exists, return true if it is touching another mino on the board
                if (board[x + indexX][y + indexY + Constants.BUFFER_ZONE] != 0) return true;
            }
        }
        //return false if all minos pass the test
        return false;
    }
    
    public boolean isOverlapped(int x, int y, int[][] board) {
        return isOverlapped(x, y, this.pieceRotation, board);
    }
    
    public boolean isOutOfBounds(int x, int y, int rotation) {
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                //ignores tile if no mino occupies it
                if (tetrominos[this.type.toInt()][indexY][rotation][indexX] == 0) continue; 
                
                //if a mino exists, return true if it is outside the left right or bottom bounds
                if (x + indexX < 0 || 
                    x + indexX > Constants.BOARD_COLS - 1 || 
                    y + indexY + Constants.BUFFER_ZONE > Constants.TOTAL_BOARD_ROWS - 1) return true;
            }
        }
        //return false if all minos pass the test
        return false;
    }
    
    public boolean isOutOfBounds(int x, int y) {
        return isOutOfBounds(x, y, this.pieceRotation);
    }

    public void hide() {
        visible = false;
    }
    public void draw(Graphics g) {
        if (!visible) return;
        int xOffset = boardRelative ? 0 : centerOffsets[0];
        int yOffset = boardRelative ? 0 : centerOffsets[1];
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (tetrominos[this.type.toInt()][indexY][pieceRotation][indexX] == 0) continue;
                Draw.square(indexX * Constants.PIECE_SIZE + pixelX - xOffset, 
                            indexY * Constants.PIECE_SIZE + pixelY - yOffset, 
                            tetrominos[this.type.toInt()][indexY][this.pieceRotation][indexX], 
                            g);
            }
        }
    }

    public void drawGhost(int[][] board, Graphics g) {
        if (!visible) return;
        int lowestY = Constants.TOTAL_BOARD_ROWS;
        for(int i = 0; i < Constants.BOARD_ROWS - this.boardY; i++) {
            if(isOutOfBounds(this.boardX, this.boardY + i, this.pieceRotation)) {
                lowestY = this.boardY + i - 1;
                break;
            }
            
            if(isOverlapped(this.boardX, this.boardY + i, this.pieceRotation, board)) {
                lowestY = this.boardY + i - 1;
                break;
            }
        }
        
        for (int indexY = 0; indexY < 4; indexY++) {
            for (int indexX = 0; indexX < 4; indexX++) {
                if (tetrominos[this.type.toInt()][indexY][pieceRotation][indexX] == 0) continue;
                Draw.ghostSquare(indexX * Constants.PIECE_SIZE + pixelX, 
                            (indexY + lowestY) * Constants.PIECE_SIZE, 
                            tetrominos[this.type.toInt()][indexY][this.pieceRotation][indexX], 
                            g);
            }
        }
    }
}
