package szachy.pieces;

import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Knight extends ChessPiece {
    public Knight(Player player) {
        super(player);
    }

    @Override
    public Type getType() {
        return Type.KNIGHT;
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("knight"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMovesBackend() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
        GameState state = board.getGameState();
        LinkedList<Move> moves = new LinkedList<Move>();

        for (int diffY : new int[] {-2, -1, 1, 2}) {
            for (int diffX : new int[] {-2, -1, 1, 2}) {
                if (Math.abs(diffX) != Math.abs(diffY)) {
                    Position otherPosition = position.transform(diffX, diffY);
                    if (otherPosition == null) {
                        continue;
                    }

                    Field otherField = board.getField(otherPosition);
                    if (otherField.isOccupied() && otherField.getPiece().getOwner() == this.getOwner()) {
                        continue;
                    }

                    moves.add(new Move(position, this, otherPosition, otherField.getPiece()));
                }
            }
        }

        return moves;
    }
}
