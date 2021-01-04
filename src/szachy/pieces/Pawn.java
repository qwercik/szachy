package szachy.pieces;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pawn extends ChessPiece {
    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    public Type getType() {
        return Type.PAWN;
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("pawn"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        LinkedList<Move> moves = new LinkedList<>();
        ChessBoard board = field.getBoard();
        GameState state = board.getGameState();
        Position position = field.getPosition();

        int diff = this.getOwner() == Player.WHITE ? -1 : 1;

        Position otherPosition = position.transform(diff, 0);
        if (otherPosition != null) {
            Field otherField = board.getField(otherPosition);
            if (otherField.isFree()) {
                moves.add(new Move(position, this, otherPosition, otherField.getPiece()));

                // It can be done only if the forward field is free
                boolean pawnIsOnStartPosition = position.getRow() == (7 + diff) % 7;
                otherPosition = position.transform(diff * 2, 0);
                if (pawnIsOnStartPosition && board.getField(otherPosition).isFree()) {
                    moves.add(new Move(position, this, otherPosition, board.getField(otherPosition).getPiece()));
                }
            }
        }

        for (int diffX : new int[] {-1, 1}) {
            otherPosition = position.transform(diff, diffX);
            if (otherPosition != null) {
                Field otherField = board.getField(otherPosition);
                if (otherField.isOccupied() && otherField.getPiece().getOwner() != this.getOwner()) {
                    moves.add(new Move(position, this, otherPosition, otherField.getPiece()));
                }
            }
        }

        return moves
                .stream()
                .filter(state::verifyMoveForCheck)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void makeMove(Move move) {
        super.makeMove(move);

        if (this.isBeingPromoted(move)) {
            this.handlePawnPromotion(move);
        }
    }

    public boolean isBeingPromoted(Move move) {
        int lastRow = this.getOwner() == Player.WHITE ? 0 : 7;
        return move.getEnd().getRow() == lastRow;
    }

    public void handlePawnPromotion(Move move) {
        Field endField = this.getField().getBoard().getField(move.getEnd());

        ButtonType queen = new ButtonType("Hetman");
        ButtonType rook = new ButtonType("Wieża");
        ButtonType knight = new ButtonType("Skoczek");
        ButtonType bishop = new ButtonType("Goniec");

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION, "Promocja piona",
                queen,
                rook,
                knight,
                bishop
        );
        alert.setTitle("Promocja piona");
        alert.setHeaderText("Promocja piona");
        alert.setContentText("Wybierz figurę, na którą chcesz promować piona");

        // User can close alert in theory so I'll show it him as long as he don't choose anything
        while (true) {
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() != null) {
                if (result.get() == queen) {
                    endField.setPiece(new Queen(this.getOwner()));
                } else if (result.get() == rook) {
                    endField.setPiece(new Rook(this.getOwner()));
                } else if (result.get() == knight) {
                    endField.setPiece(new Knight(this.getOwner()));
                } else {
                    endField.setPiece(new Bishop(this.getOwner()));
                }

                break;
            }
        }
    }
}
