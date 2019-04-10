package Players;

/**
 * Created by Gleb on 10/04/2019.
 */
public interface PlayerInterface {
    public int getMove();
    public boolean getHasWon();
    public void updateOpponentsMove(int theirMove);
}
