package szachy.pieces;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import szachy.*;

import java.util.LinkedList;

public class Pawn extends ChessPiece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("pawn"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        LinkedList<Move> moves = new LinkedList<>();
        ChessBoard board = field.getBoard();
        Position position = field.getPosition();

        if (!this.isOwnedByCurrentPlayer()) {
            return moves;
        }

        int diff = this.getOwner() == Player.WHITE ? -1 : 1;

        Position otherPosition = position.transform(diff, 0);
        if (otherPosition != null) {
            Field otherField = board.getField(otherPosition);
            if (otherField.isFree()) {
                moves.add(new Move(position, otherPosition));

                // It can be done only if the forward field is free
                boolean pawnIsOnStartPosition = position.getRow() == (7 + diff) % 7;
                otherPosition = position.transform(diff * 2, 0);
                if (pawnIsOnStartPosition && board.getField(otherPosition).isFree()) {
                    moves.add(new Move(position, otherPosition));
                }
            }
        }

        for (int diffX : new int[] {-1, 1}) {
            otherPosition = position.transform(diff, diffX);
            if (otherPosition != null) {
                Field otherField = board.getField(otherPosition);
                if (otherField.isBusy() && otherField.getPiece().getOwner() != this.getOwner()) {
                    moves.add(new Move(position, otherPosition));
                }
            }
        }

        return moves;
    }
}
