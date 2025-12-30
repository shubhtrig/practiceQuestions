import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        while (game.getStatus() == GameStatus.IN_PROGRESS) {

            game.display();

            System.out.print("Select column player " + game.getCurrentPlayer().name());
            int col = scanner.nextInt();

            if (!game.makeMove(game.getCurrentPlayer(), col)) {
                System.out.println("Cannot make the move, please select another column");
                continue;
            }

            if (game.getStatus() == GameStatus.WON) {
                System.out.println("Player " + game.getCurrentPlayer().name() + " won");
                break;
            }

            if (game.getStatus() == GameStatus.DRAW) {
                System.out.println("game ended in a draw");
                break;
            }
        }
    }
}