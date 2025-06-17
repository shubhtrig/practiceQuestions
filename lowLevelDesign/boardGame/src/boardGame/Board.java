package boardGame;

public interface Board {
    boolean makeMove(Move move, Player p);
    void initialize(int size);
    void displayBoard();
}
