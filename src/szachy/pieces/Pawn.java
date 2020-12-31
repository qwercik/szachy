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

        Field field = this.getField();
        ChessBoard board = field.getBoard();
        Position position = field.getPosition();

        Position oneFieldAbove = this.getFrontField(1);
        if (board.getField(oneFieldAbove).isFree()) {
            moves.add(new Move(position, oneFieldAbove));
        }

        return moves;
    }

    private boolean hasAlreadyMoved() {
        Position position = this.getField().getPosition();
        if (this.owner.getType() == Player.Type.WHITE) {
            return position.getRow() == 6;
        } else {
            return position.getRow() == 1;
        }
    }

    private Position getFrontField(int count) {
        Position position = this.getField().getPosition();

        int diff = count * (this.owner.getType() == Player.Type.WHITE ? -1 : 1);
        int row = position.getRow() + diff;
        int column = position.getColumn();

        if (row < 0 || row > 8) {
            return null;
        }

        return new Position(row, column);
    }
}
