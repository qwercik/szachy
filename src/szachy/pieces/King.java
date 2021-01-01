package szachy.pieces;

import javafx.scene.image.Image;
import szachy.*;

import java.util.LinkedList;

public class King extends ChessPiece {
    public King(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("king"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
        LinkedList<Move> moves = new LinkedList<Move>();

        for (int diffY : new int[] {-1, 0, 1}) {
            for (int diffX : new int[] {-1, 0, 1}) {
                if (diffX != 0 || diffY != 0) {
                    Position otherPosition = position.transform(diffX, diffY);
                    if (otherPosition == null) {
                        continue;
                    }

                    Field otherField = board.getField(otherPosition);
                    if (otherField.isBusy() && otherField.getPiece().getOwner() == this.getOwner()) {
                        continue;
                    }

                    moves.add(new Move(position, otherPosition));
                }
            }
        }

        return moves;
    }
}
