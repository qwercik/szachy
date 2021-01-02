package szachy;

public class Move {
    private Position start;
    private Position end;
    private ChessPiece removedPiece;

    public Move(Position start, Position end, ChessPiece removedPiece) {
        this.start = start;
        this.end = end;
        this.removedPiece = removedPiece;
    }

    public Position getStart() {
        return this.start;
    }

    public Position getEnd() {
        return this.end;
    }

    public ChessPiece getRemovedPiece() {
        return this.removedPiece;
    }

    public Move opposite() {
        return new Move(this.end, this.start, this.removedPiece);
    }
}

