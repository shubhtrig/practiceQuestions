package boardGame;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;
    private final GameRules gameRules;
    private GameStatus gameStatus;
    private final MoveStrategy moveStrategy;

    public Game(Board board, Player p1, Player p2, GameRules gameRules, MoveStrategy moveStrategy) {
        this.board = board;
        this.players = new Player[]{p1, p2};
        this.gameRules = gameRules;
        this.currentPlayerIndex = 0;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moveStrategy = moveStrategy;
    }

    public void startGame() {
        while (gameStatus == GameStatus.IN_PROGRESS) {
            board.displayBoard();
            Player currentPlayer = players[currentPlayerIndex];
            Move move = moveStrategy.getNextMove(currentPlayer);
            if (!board.makeMove(move, currentPlayer)) {
                System.out.println("Invalid move! Please try again");
                continue;
            }

            if (gameRules.isWinningMove(board, move)) {
                board.displayBoard();
                System.out.println("Player "+ currentPlayer.name() + " won!!!");
                gameStatus = GameStatus.WON;
                break;
            }

            if (gameRules.isDraw(board)) {
                board.displayBoard();
                System.out.println("It's a draw!");
                gameStatus = GameStatus.DRAW;
                break;
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }
    }
}
