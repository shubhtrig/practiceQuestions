package boardGame.tictactoe;

import boardGame.*;

public class TictactoeBoard implements Board {
    private Cell[][] board;
    private int size;

    @Override
    public boolean makeMove(Move move, Player p) {
        TicTacToeMove tMove = (TicTacToeMove) move;
        if (board[tMove.row()][tMove.col()].symbol != Symbol.EMPTY)
            return false;

        board[tMove.row()][tMove.col()].symbol = p.symbol();
        return true;
    }

    @Override
    public void initialize(int size) {
        this.size = size;
        board = new Cell[size][size];
        this.size = 3;
        for (int i =0; i<size; i++) {
            for (int j = 0; j<size; j++) {
                board[i][j] = new Cell(i, j, Symbol.EMPTY);
            }
        }
    }

    @Override
    public void displayBoard() {
        for (int i =0; i<this.size; i++) {
            for (int j = 0; j<this.size; j++) {
                System.out.print(board[i][j].symbol.getValue());
                if (j != this.size - 1)
                    System.out.print("|");
            }
            System.out.println('\n');
        }
    }

    public int getSize() {
        return this.size;
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }
}
