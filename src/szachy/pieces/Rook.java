package szachy.pieces;

import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Rook extends ChessPiece {
    public Rook(Player player) {
        super(player);
    }

    @Override
    public Type getType() {
        return Type.ROOK;
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("rook"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        ChessBoard board = this.getField().getBoard();
        GameState state = board.getGameState();
        Position position = this.getField().getPosition();
        LinkedList<Move> moves = new LinkedList<Move>();

        for (int diffY : new int[] {-1, 0, 1}) {
            for (int diffX : new int[] {-1, 0, 1}) {
                if (diffX == 0 ^ diffY == 0) {
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
                                moves.add(new Move(position, this, otherPosition, otherField.getPiece()));
                            }

                            break;
                        }

                        moves.add(new Move(position, this, otherPosition, otherField.getPiece()));
                        currentDiffX += diffX;
                        currentDiffY += diffY;
                    }
                }
            }
        }

        return moves
                .stream()
                .filter(state::verifyMoveForCheck)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
