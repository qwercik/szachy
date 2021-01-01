package szachy.pieces;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import szachy.*;

import java.util.LinkedList;
import java.util.Optional;

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
        ChessBoard board = field.getBoard();
        Position position = field.getPosition();

        if (!this.isOwnedByCurrentPlayer()) {
            return moves;
        }

        int diff = this.getOwner() == Player.WHITE ? -1 : 1;

        Position otherPosition = position.transform(diff, 0);
        if (otherPosition != null) {
            Field otherField = board.getField(otherPosition);
            if (otherField.isFree()) {
                moves.add(new Move(position, otherPosition));

                // It can be done only if the forward field is free
                boolean pawnIsOnStartPosition = position.getRow() == (7 + diff) % 7;
                otherPosition = position.transform(diff * 2, 0);
                if (pawnIsOnStartPosition && board.getField(otherPosition).isFree()) {
                    moves.add(new Move(position, otherPosition));
                }
            }
        }

        for (int diffX : new int[] {-1, 1}) {
            otherPosition = position.transform(diff, diffX);
            if (otherPosition != null) {
                Field otherField = board.getField(otherPosition);
                if (otherField.isBusy() && otherField.getPiece().getOwner() != this.getOwner()) {
                    moves.add(new Move(position, otherPosition));
                }
            }
        }

        return moves;
    }

    @Override
    public void makeMove(Move move) {
        super.makeMove(move);

        ChessBoard board = this.getField().getBoard();
        Field startField = board.getField(move.getStart());
        Field endField = board.getField(move.getEnd());
        endField.setPiece(startField.getPiece());
        startField.setPiece(null);

        int lastRow = this.getOwner() == Player.WHITE ? 0 : 7;
        if (move.getEnd().getRow() == lastRow) {
            // User can close alert in theory so I'll show it him as long as he don't choose anything
            ButtonType queen = new ButtonType("Hetman");
            ButtonType rook = new ButtonType("Wieża");
            ButtonType knight = new ButtonType("Skoczek");
            ButtonType bishop = new ButtonType("Goniec");

            while (true) {
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
}
