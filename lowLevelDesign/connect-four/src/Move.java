public class Move {
    private Player player;
    private int column;
    private int row;

    public Move(Player player, int column, int row) {
        this.player = player;
        this.column = column;
        this.row = row;
    }

    public Player getPlayer() {
        return player;
    }
    public int getColumn() {
        return column;
    }
    public int getRow() {
        return row;
    }
}
