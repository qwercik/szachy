package szachy.pieces;

import javafx.scene.image.Image;
import szachy.*;

import java.util.LinkedList;

public class Queen extends ChessPiece {
    public Queen(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("queen"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        ChessBoard board = this.getField().getBoard();
        Position position = this.getField().getPosition();
        LinkedList<Move> moves = new LinkedList<Move>();

        for (int diffY : new int[] {-1, 0, 1}) {
            for (int diffX : new int[] {-1, 0, 1}) {
                if (diffX != 0 || diffY != 0) {
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
                        currentDiffX += diffX;
                        currentDiffY += diffY;
                    }
                }
            }
        }

        return moves;
    }
}
