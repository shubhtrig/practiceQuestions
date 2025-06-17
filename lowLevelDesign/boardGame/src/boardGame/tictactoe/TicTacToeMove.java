package boardGame.tictactoe;

import boardGame.Move;

public record TicTacToeMove(int row, int col) implements Move {
}
