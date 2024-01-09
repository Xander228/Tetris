
public class PieceBag
{
    private int[] pieceBag;
    private int bagIndex;
    
    public PieceBag()
    {
        pieceBag = new int[7];
        bagIndex = 0;
        for (int i = 0; i < 7; i++) pieceBag[i] = i;
        shuffleBag();
    }

    public int pullNewPiece() {
        int newPiece = pieceBag[bagIndex];
        bagIndex++;
        if (bagIndex >= 7) {
            shuffleBag();
            bagIndex = 0;
        }
        return newPiece;
    }
    
    private void shuffleBag() {
        for (int i = 0; i < 30; i++) {
            int buffer = 0;
            int index1 = (int)(Math.random() * 6) + 1;
            int index2 = (int)(Math.random() * 6) + 1;
            buffer = pieceBag[index1];
            pieceBag[index1] = pieceBag[index2];
            pieceBag[index2] = buffer;
        }
    }
}
