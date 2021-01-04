package szachy.engine;

import szachy.pieces.ChessPiece;

public class Move {
    public Move(Position start, Position end, ChessPiece movedPiece, ChessPiece removedPiece) {
        this.start = start;
        this.end = end;
        this.movedPiece = movedPiece;
        this.removedPiece = removedPiece;
    }

    public Position getStart() {
        return this.start;
    }

    public ChessPiece getMovedPiece() {
        return this.movedPiece;
    }

    public Position getEnd() {
        return this.end;
    }

    public ChessPiece getRemovedPiece() {
        return this.removedPiece;
    }

    private final Position start;
    private final Position end;
    private final ChessPiece movedPiece;
    private final ChessPiece removedPiece;
}
