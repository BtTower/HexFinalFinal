package Players;


public interface PlayerInterface {
    public int getMove();
    public boolean getHasWon();
    public void updateOpponentsMove(int theirMove);
}
