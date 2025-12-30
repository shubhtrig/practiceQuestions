public class Board {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private final DiscColor[][] grid;

    public Board() {
        this.grid = new DiscColor[ROWS][COLUMNS];
    }

    public boolean canPlace(int col) {
        if (col >= COLUMNS || col <0)
            return false;

        return this.grid[0][col] == null;
    }

    public int placeDisc(int col, DiscColor color) {
        if (!canPlace(col))
            return -1;

        for (int i = ROWS -1; i>=0; i--) {
            if (this.grid[i][col] == null) {
                this.grid[i][col] = color;
                return i;
            }
        }
        return -1;
    }

    public boolean checkWin(int row, int col, DiscColor color) {
         int[][] directions = {
                 {1,0}, // vertical
                 {0,1}, // horizontal
                 {1,1}, // diagonal
                 {-1,1} // up diagonal

         };

        for (int[] direction : directions) {
            int count = 1;
            count += checkColor(row, col, direction[0], direction[1], color);
            count += checkColor(row, col, -direction[0], -direction[1], color);
            if (count >= 4)
                return true;
        }

        return false;
    }

    public boolean isFull() {
        for (int c = 0; c < COLUMNS; c++) {
            if (this.grid[0][c] == null)
                return false;
        }
        return true;
    }

    public void clearCell(int row, int col) {
        if (isValid(row, col)) {
            this.grid[row][col] = null;
        }
    }

    public void display() {
        for (DiscColor[] row : grid) {
            for (DiscColor d : row) {
                if (d == null)
                    System.out.print("_" + " ");
                else
                    System.out.print(d.name() + " ");
            }
            System.out.println();
        }
    }

    private int checkColor(int row, int col, int dirR, int dirC, DiscColor color) {
        int r = row + dirR;
        int c = col + dirC;
        int count = 0;
        while (isValid(r, c) && this.grid[r][c] == color) {
            count++;
            r += dirR;
            c += dirC;
        }
        return count;
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < ROWS && c >=0 && c <COLUMNS;
    }
}
