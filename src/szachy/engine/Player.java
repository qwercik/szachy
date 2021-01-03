package szachy.engine;

public enum Player {
    WHITE, BLACK;

    public Player opposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    public Position getRelativePosition(Position position) {
        if (this == BLACK) {
            return position;
        }

        return new Position(ChessBoard.SIZE - 1 - position.getRow(), position.getColumn());
    }

    public String getPluralName() {
        return this == WHITE ? "białe" : "czarne";
    }
}
