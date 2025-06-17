package boardGame.tictactoe;

public class Cell {
    int x;
    int y;
    Symbol symbol;

    public Cell(int i, int j, Symbol symbol) {
        this.x = i;
        this.y = j;
        this.symbol = symbol;
    }
}
