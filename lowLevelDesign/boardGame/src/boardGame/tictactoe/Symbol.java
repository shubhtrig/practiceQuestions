package boardGame.tictactoe;

public enum Symbol {
    X('X'), O('O'), EMPTY('*');
    private final Character sign;
    Symbol(Character sign) {
        this.sign = sign;
    }

    public Character getValue() {
        return this.sign;
    }
}
