package boardGame;

import boardGame.tictactoe.Symbol;
import boardGame.tictactoe.TicTacToeMoveStrategy;
import boardGame.tictactoe.TictactoeBoard;
import boardGame.tictactoe.TictactoeGameRules;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TictactoeBoard board = new TictactoeBoard();
        board.initialize(3);

        Player p1 = new Player("Sh", Symbol.O);
        Player p2 = new Player("So", Symbol.X);
        GameRules gameRules = new TictactoeGameRules();

        Game game = new Game(board, p1, p2, gameRules, new TicTacToeMoveStrategy());
        game.startGame();
    }
}