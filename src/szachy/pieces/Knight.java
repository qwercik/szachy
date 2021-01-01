package szachy.pieces;

import javafx.scene.image.Image;
import szachy.*;

import java.util.LinkedList;

public class Knight extends ChessPiece {
    public Knight(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("knight"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
        LinkedList<Move> moves = new LinkedList<Move>();

        for (int diffY : new int[] {-2, -1, 1, 2}) {
            for (int diffX : new int[] {-2, -1, 1, 2}) {
                if (Math.abs(diffX) != Math.abs(diffY)) {
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
