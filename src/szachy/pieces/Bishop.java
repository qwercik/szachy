package szachy.pieces;

import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;

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
    public LinkedList<Position> getAllPossibleDestinationsBackend() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
        LinkedList<Position> destinations = new LinkedList<Position>();

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
                            destinations.add(otherPosition);
                        }

                        break;
                    }

                    destinations.add(otherPosition);
                    currentDiffY += diffY;
                    currentDiffX += diffX;
                }
            }
        }

        return destinations;
    }
}
