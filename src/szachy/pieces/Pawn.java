package szachy.pieces;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;
import java.util.Optional;

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
    public LinkedList<Position> getAllPossibleDestinationsBackend() {
        LinkedList<Position> destinations = new LinkedList<>();
        ChessBoard board = field.getBoard();
        GameState state = board.getGameState();
        Position position = field.getPosition();

        int diff = this.getOwner() == Player.WHITE ? -1 : 1;
        int myPawnStartRow = this.getOwner() == Player.WHITE ? 6 : 1;
        int opponentPawnStartRow = this.getOwner() == Player.WHITE ? 1 : 6;
        int enPassantRow = this.getOwner() == Player.WHITE ? 3 : 4;

        Position otherPosition = position.transform(diff, 0);
        if (otherPosition != null) {
            Field otherField = board.getField(otherPosition);
            if (otherField.isFree()) {
                destinations.add(otherPosition);

                // It can be done only if the forward field is free
                boolean pawnIsOnStartPosition = position.getRow() == myPawnStartRow;
                otherPosition = position.transform(diff * 2, 0);
                if (pawnIsOnStartPosition && board.getField(otherPosition).isFree()) {
                    destinations.add(otherPosition);
                }
            }
        }

        for (int diffX : new int[] {-1, 1}) {
            otherPosition = position.transform(diff, diffX);
            if (otherPosition != null) {
                Field otherField = board.getField(otherPosition);
                if (otherField.isOccupied() && otherField.getPiece().getOwner() != this.getOwner()) {
                    destinations.add(otherPosition);
                }
            }

            // Check en passant
            if (position.getRow() == enPassantRow) {
                otherPosition = position.transform(0, diffX);
                if (otherPosition != null) {
                    Field field = board.getField(otherPosition);
                    if (field.isOccupied()) {
                        ChessPiece piece = field.getPiece();
                        if (piece.getType() == Type.PAWN) {
                            Move lastMove = state.getMovesHistory().getLast();

                            if (lastMove.getMovedPieceEndPosition().equals(otherPosition) && lastMove.getMovedPieceStartPosition().getRow() == opponentPawnStartRow) {
                                // Detected en passant
                                destinations.add(position.transform(diff, diffX));
                            }
                        }
                    }
                }
            }
        }

        return destinations;
    }

    @Override
    public Move makeMoveBackend(Position destination) {
        Move move;
        ChessBoard board = this.getField().getBoard();
        Position position = this.getField().getPosition();

        // En passant
        int diffX = destination.getColumn() - position.getColumn();
        if (board.getField(destination).isFree() && diffX != 0) {
            Position positionToRemove = position.transform(0, diffX);
            move = new Move(position, destination, this, board.getField(positionToRemove).getPiece(), positionToRemove);

            board.getField(positionToRemove).setPiece(null);
            super.makeMoveBackend(destination);
        } else {
            move = super.makeMoveBackend(destination);
        }

        if (this.isBeingPromoted(destination)) {
            this.handlePawnPromotion(destination);
        }

        return move;
    }

    @Override
    public void takeBackMoveBackend(Move move) {
        super.takeBackMoveBackend(move);
    }

    public boolean isBeingPromoted(Position destination) {
        int lastRow = this.getOwner() == Player.WHITE ? 0 : 7;
        return destination.getRow() == lastRow;
    }

    public void handlePawnPromotion(Position destination) {
        Field endField = this.getField().getBoard().getField(destination);

        ButtonType queenChosen = new ButtonType("Hetman");
        ButtonType rookChosen = new ButtonType("Wieża");
        ButtonType knightChosen = new ButtonType("Skoczek");
        ButtonType bishopChosen = new ButtonType("Goniec");

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION, "Promocja piona",
                queenChosen,
                rookChosen,
                knightChosen,
                bishopChosen
        );
        alert.setTitle("Promocja piona");
        alert.setHeaderText("Promocja piona");
        alert.setContentText("Wybierz figurę, na którą chcesz promować piona");

        // User can close alert in theory so I'll show it him as long as he don't choose anything
        while (true) {
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent()) {
                if (result.get() == queenChosen) {
                    Queen queen = new Queen(this.getOwner());
                    this.getOwner().getQueens().add(queen);
                    endField.setPiece(queen);
                } else if (result.get() == rookChosen) {
                    Rook rook = new Rook(this.getOwner());
                    this.getOwner().getRooks().add(rook);
                    endField.setPiece(rook);
                } else if (result.get() == knightChosen) {
                    Knight knight = new Knight(this.getOwner());
                    this.getOwner().getKnights().add(knight);
                    endField.setPiece(knight);
                } else {
                    Bishop bishop = new Bishop(this.getOwner());
                    this.getOwner().getBishops().add(bishop);
                    endField.setPiece(bishop);
                }

                break;
            }
        }
    }

    public static boolean isAttacking(GameState state, Position position, Player attacker) {
        int diff = attacker == Player.WHITE ? 1 : -1;
        for (int diffX : new int[] {-1, 1}) {
            Position otherPosition = position.transform(diff, diffX);
            if (otherPosition != null) {
                Field otherField = state.getBoard().getField(otherPosition);
                if (otherField.isOccupied()) {
                    ChessPiece otherPiece = otherField.getPiece();
                    if (otherPiece.getOwner() == attacker && otherPiece.getType() == Type.PAWN) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
