
public class PieceBag
{
    private int[] pieceBag; //Declare an int array to store the pieces in the bag
    private int bagIndex; //Declare an int to store what index of the bag should be pulled from next

    public PieceBag()
    {
        pieceBag = new int[7]; //Initializes the pieceBag as an int array with 7 indexes
        bagIndex = 0; //Initializes the bagIndex to 0, or the first index of the bag
        for (int i = 0; i < 7; i++) pieceBag[i] = i; //Initializes the values of the ints in pieceBag to numbers 1-7
        shuffleBag(); //Shuffles the indexes in piece bag
    }

    public int pullNewPiece() {
        int newPiece = pieceBag[bagIndex]; //Pulls a piece from the bag at the current selected index
        bagIndex++; //Increment the index for next time
        //If the index exceeds the length of the array its is reset to 0 and the bag is reshuffled
        if (bagIndex >= 7) {
            bagIndex = 0; //Resets index to 0
            shuffleBag(); //Shuffles the bag
        }
        return newPiece; //Returns the piece that was pulled
    }

    //Rearranges the indexes of the pieceBag to create a random bag to pull from, this is done to ensure an equal number of pieces but a random order
    private void shuffleBag() {
        //Switches two indexes with each other 30 times
        for (int i = 0; i < 30; i++) {
            //Select 2 random indexes
            int index1 = (int)(Math.random() * 6);
            int index2 = (int)(Math.random() * 6);

            int buffer = pieceBag[index1]; //Copy the value of index1
            pieceBag[index1] = pieceBag[index2]; //Write index2 to index1
            pieceBag[index2] = buffer; //Write the original value of index1 to index2
        }
    }
}
