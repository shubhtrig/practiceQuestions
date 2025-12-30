import java.util.Stack;

public class Game {
    private final Player p1;
    private final Player p2;
    private Player currentPlayer;

    private final Board board;
    private GameStatus status;
    private Player winner;
    private Stack<Move> moveHistory;

    public Game() {
        this.p1 = new Player("P1", DiscColor.R);
        this.p2 = new Player("P2", DiscColor.Y);
        this.board = new Board();
        this.status  = GameStatus.IN_PROGRESS;
        this.currentPlayer = p1;
        this.moveHistory = new Stack<>();
    }

    public boolean makeMove(Player p, int col) {
        if (status != GameStatus.IN_PROGRESS)
            return false;

        if (!board.canPlace(col))
            return false;

        int row = board.placeDisc(col, currentPlayer.color());
        moveHistory.push(new Move(p, col, row));

        if (board.checkWin(row, col, p.color())) {
            this.status = GameStatus.WON;
            this.winner = p;
        } else if (board.isFull()) {
            this.status = GameStatus.DRAW;
        } else {
            currentPlayer = p1 == currentPlayer ? p2 : p1;
        }
        return true;
    }

    public void undo() {
        if (moveHistory.isEmpty()) {
            return;
        }

        Move lastMove = moveHistory.pop();
        this.currentPlayer = lastMove.getPlayer();
        board.clearCell(lastMove.getRow(), lastMove.getColumn());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void display() {
        board.display();
    }
}
