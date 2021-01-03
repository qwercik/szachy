package szachy.engine;

import szachy.pieces.ChessPiece;

public class Move {
    public Move(Position start, ChessPiece startPiece, Position end, ChessPiece endPiece) {
        this.start = start;
        this.startPiece = startPiece;
        this.end = end;
        this.endPiece = endPiece;
    }

    public Position getStart() {
        return this.start;
    }

    public ChessPiece getStartPiece() {
        return this.startPiece;
    }

    public Position getEnd() {
        return this.end;
    }

    public ChessPiece getEndPiece() {
        return this.endPiece;
    }

    public Move getReversedMove() {
        return new Move(this.end, this.endPiece, this.start, this.startPiece);
    }

    private Position start;
    private ChessPiece startPiece;
    private Position end;
    private ChessPiece endPiece;
}
