package szachy.engine;

import szachy.pieces.ChessPiece;

public class Move {
    public Move(Position start, Position end, ChessPiece movedPiece, ChessPiece removedPiece, Position removedPiecePosition) {
        this.start = start;
        this.end = end;
        this.movedPiece = movedPiece;
        this.removedPiece = removedPiece;
        this.removedPiecePosition = removedPiecePosition;
    }

    public Position getMovedPieceStartPosition() {
        return this.start;
    }

    public ChessPiece getMovedPiece() {
        return this.movedPiece;
    }

    public Position getMovedPieceEndPosition() {
        return this.end;
    }

    public ChessPiece getRemovedPiece() {
        return this.removedPiece;
    }

    public Position getRemovedPiecePosition() {
        return this.removedPiecePosition;
    }

    private final Position start;
    private final Position end;
    private final ChessPiece movedPiece;
    private final ChessPiece removedPiece;

    private final Position removedPiecePosition;
}
