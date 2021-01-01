package szachy.pieces;

import javafx.scene.image.Image;
import szachy.*;

import java.util.LinkedList;

public class Bishop extends ChessPiece {
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("bishop"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        Position position = this.getField().getPosition();
        ChessBoard board = this.getField().getBoard();
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
                    if (otherField.isBusy()) {
                        if (otherField.getPiece().getOwner() != this.getOwner()) {
                            moves.add(new Move(position, otherPosition));
                        }

                        break;
                    }

                    moves.add(new Move(position, otherPosition));
                    currentDiffY += diffY;
                    currentDiffX += diffX;
                }
            }
        }

        return moves;
    }
}
