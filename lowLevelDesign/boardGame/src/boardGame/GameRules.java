package boardGame;

public interface GameRules {
    boolean isWinningMove(Board board, Move move);
    boolean isDraw(Board board);
}
