package szachy.pieces;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;

public class King extends ChessPiece {
    public King(Player player) {
        super(player);
    }

    @Override
    public Type getType() {
        return Type.KING;
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("king"));
    }

    @Override
    public Move makeMoveBackend(Position destination) {
        ChessBoard board = this.getField().getBoard();
        Position oldKingPosition = this.getField().getPosition();
        Position newKingPosition = destination;

        Field oldKingField = board.getField(oldKingPosition);
        Field newKingField = board.getField(newKingPosition);

        if (newKingPosition.getColumn() - oldKingPosition.getColumn() == -2) {
            // Castle left
            Position oldRookPosition = new Position(oldKingPosition.getRow(), 0);
            Position newRookPosition = new Position(oldRookPosition.getRow(), newKingPosition.getColumn() + 1);

            Field oldRookField = board.getField(oldRookPosition);
            Field newRookField = board.getField(newRookPosition);

            Move move = new Move(oldKingPosition, newKingPosition, oldKingField.getPiece(), null, newKingPosition);

            newRookField.setPiece(oldRookField.getPiece());
            oldRookField.setPiece(null);

            newKingField.setPiece(oldKingField.getPiece());
            oldKingField.setPiece(null);

            return move;
        } else if (newKingPosition.getColumn() - oldKingPosition.getColumn() == 2) {
            // Castle right
            Position oldRookPosition = new Position(oldKingPosition.getRow(), 7);
            Position newRookPosition = new Position(oldKingPosition.getRow(), newKingPosition.getColumn() - 1);

            Field oldRookField = board.getField(oldRookPosition);
            Field newRookField = board.getField(newRookPosition);

            Move move = new Move(oldKingPosition, newKingPosition, oldKingField.getPiece(), null, newKingPosition);

            newRookField.setPiece(oldRookField.getPiece());
            oldRookField.setPiece(null);

            newKingField.setPiece(oldKingField.getPiece());
            oldKingField.setPiece(null);

            return move;
        } else {
            return super.makeMoveBackend(destination);
        }
    }

    @Override
    public void takeBackMoveBackend(Move move) {
        ChessBoard board = this.getField().getBoard();
        Position oldKingPosition = move.getMovedPieceStartPosition();
        Position newKingPosition = move.getMovedPieceEndPosition();

        Field oldKingField = board.getField(oldKingPosition);
        Field newKingField = board.getField(newKingPosition);

        if (newKingPosition.getColumn() - oldKingPosition.getColumn() == -2) {
            // Castle left
            Position oldRookPosition = new Position(oldKingPosition.getRow(), 0);
            Position newRookPosition = new Position(oldRookPosition.getRow(), newKingPosition.getColumn() + 1);

            Field oldRookField = board.getField(oldRookPosition);
            Field newRookField = board.getField(newRookPosition);

            oldRookField.setPiece(newRookField.getPiece());
            newRookField.setPiece(null);

            oldKingField.setPiece(newKingField.getPiece());
            newKingField.setPiece(null);
        } else if (newKingPosition.getColumn() - oldKingPosition.getColumn() == 2) {
            // Castle right
            Position oldRookPosition = new Position(oldKingPosition.getRow(), 7);
            Position newRookPosition = new Position(oldKingPosition.getRow(), newKingPosition.getColumn() - 1);

            Field oldRookField = board.getField(oldRookPosition);
            Field newRookField = board.getField(newRookPosition);

            oldRookField.setPiece(newRookField.getPiece());
            newRookField.setPiece(null);

            oldKingField.setPiece(newKingField.getPiece());
            newKingField.setPiece(null);
        } else {
            super.takeBackMoveBackend(move);
        }
    }

    @Override
    public LinkedList<Position> getAllPossibleDestinationsBackend() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
        LinkedList<Position> destinations = new LinkedList<Position>();

        for (int diffY : new int[] {-1, 0, 1}) {
            for (int diffX : new int[] {-1, 0, 1}) {
                if (diffX != 0 || diffY != 0) {
                    Position otherPosition = position.transform(diffY, diffX);
                    if (otherPosition == null) {
                        continue;
                    }

                    Field otherField = board.getField(otherPosition);
                    if (otherField.isOccupied() && otherField.getPiece().getOwner() == this.getOwner()) {
                        continue;
                    }

                    destinations.add(otherPosition);
                }
            }
        }

        if (!this.hasAlreadyMoved()) {
            if (this.canCastle(-1)) {
                Position otherPosition = new Position(position.getRow(), position.getColumn() - 2);
                destinations.add(otherPosition);
            }

            if (this.canCastle(1)) {
                Position otherPosition = new Position(position.getRow(), position.getColumn() + 2);
                destinations.add(otherPosition);
            }
        }

        return destinations;
    }

    private boolean canCastle(int direction) {
        ChessBoard board = this.getField().getBoard();
        Position position = this.getField().getPosition();

        Position shouldContainRookPosition = new Position(position.getRow(), direction > 0 ? 7 : 0);
        Field shouldContainRook = board.getField(shouldContainRookPosition);
        if (shouldContainRook.isFree()) {
            return false;
        }

        ChessPiece piece = shouldContainRook.getPiece();
        if (piece.getType() != Type.ROOK || piece.hasAlreadyMoved()) {
            return false;
        }

        while (true) {
            position = position.transform(0, direction);

            if (position.equals(shouldContainRookPosition)) {
                break;
            }

            if (board.getField(position).isOccupied()) {
                return false;
            }
        }

        return true;
    }
}
