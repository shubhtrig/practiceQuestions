package boardGame.tictactoe;

import boardGame.Board;
import boardGame.GameRules;
import boardGame.Move;

public class TictactoeGameRules implements GameRules {
    @Override
    public boolean isWinningMove(Board board, Move move) {
        TictactoeBoard tictactoeBoard = (TictactoeBoard) board;
        TicTacToeMove ticTacToeMove = (TicTacToeMove) move;
        int row = ticTacToeMove.row();
        int col = ticTacToeMove.col();
        boolean won = false;
        Symbol symbol = tictactoeBoard.getCell(ticTacToeMove.row(), ticTacToeMove.col()).symbol;

        // check row
        won = true;
        for (int i=0; i<tictactoeBoard.getSize(); i++) {
            if (tictactoeBoard.getCell(ticTacToeMove.row(), i).symbol != symbol) {
                won = false;
                break;
            }
        }
        if (won) return true;

        // check column
        won = true;
        for (int i=0; i<tictactoeBoard.getSize(); i++) {
            if (tictactoeBoard.getCell(i, ticTacToeMove.col()).symbol != symbol) {
                won = false;
                break;
            }
        }
        if (won) return true;

        if (row == col) {
            won = true;
            for (int i = 0; i < tictactoeBoard.getSize(); i++) {
                if (tictactoeBoard.getCell(i, i).symbol != symbol) {
                    won = false;
                    break;
                }
            }
            if (won) return true;

            won = true;
            for (int i = 0; i < tictactoeBoard.getSize(); i++) {
                if (tictactoeBoard.getCell(tictactoeBoard.getSize()-i-1, i).symbol != symbol) {
                    won = false;
                    break;
                }
            }
        }
        return won;
    }

    @Override
    public boolean isDraw(Board board) {
        TictactoeBoard tictactoeBoard = (TictactoeBoard) board;
        int size = tictactoeBoard.getSize();
        for (int i = 0; i<size; i++) {
            for (int j = 0; j< size; j++) {
                if (tictactoeBoard.getCell(i, j).symbol == Symbol.EMPTY)
                    return false;
            }
        }
        return true;
    }
}
