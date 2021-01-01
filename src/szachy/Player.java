package szachy;

public enum Player {
    WHITE, BLACK;

    public Player toggle() {
        return this == WHITE ? BLACK : WHITE;
    }
}
