package szachy.pieces;

import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Bishop extends ChessPiece {
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public Type getType() {
        return Type.BISHOP;
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("bishop"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMovesBackend() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
        GameState state = board.getGameState();
        LinkedList<Move> moves = new LinkedList<Move>();

        for (int diffY : new int[] {-1, 1}) {
            for (int diffX : new int[] {-1, 1}) {
                int currentDiffY = diffY;
                int currentDiffX = diffX;

                while (true) {
                    Position otherPosition = position.transform(currentDiffY, currentDiffX);
                    if (otherPosition == null) {
                        break;
                    }

                    Field otherField = board.getField(otherPosition);
                    if (otherField.isOccupied()) {
                        if (otherField.getPiece().getOwner() != this.getOwner()) {
                            moves.add(new Move(position, otherPosition, this, otherField.getPiece()));
                        }

                        break;
                    }

                    moves.add(new Move(position, otherPosition, this, otherField.getPiece()));
                    currentDiffY += diffY;
                    currentDiffX += diffX;
                }
            }
        }

        return moves;
    }
}
