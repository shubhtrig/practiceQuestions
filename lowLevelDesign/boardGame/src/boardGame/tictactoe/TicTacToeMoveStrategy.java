package boardGame.tictactoe;

import boardGame.Move;
import boardGame.MoveStrategy;
import boardGame.Player;

import java.util.Scanner;

public class TicTacToeMoveStrategy implements MoveStrategy {
    private final Scanner scanner;

    public TicTacToeMoveStrategy() {
        scanner = new Scanner(System.in);
    }

    @Override
    public Move getNextMove(Player p) {
        System.out.println("Player " + p.name() + "'s chance");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new TicTacToeMove(x, y);

    }
}
