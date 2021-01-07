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
    public LinkedList<Move> getAllPossibleMovesBackend() {
        LinkedList<Move> moves = new LinkedList<>();
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
                moves.add(new Move(position, otherPosition, this, otherField.getPiece(), otherPosition));

                // It can be done only if the forward field is free
                boolean pawnIsOnStartPosition = position.getRow() == myPawnStartRow;
                otherPosition = position.transform(diff * 2, 0);
                if (pawnIsOnStartPosition && board.getField(otherPosition).isFree()) {
                    moves.add(new Move(position, otherPosition, this, board.getField(otherPosition).getPiece(), otherPosition));
                }
            }
        }

        for (int diffX : new int[] {-1, 1}) {
            otherPosition = position.transform(diff, diffX);
            if (otherPosition != null) {
                Field otherField = board.getField(otherPosition);
                if (otherField.isOccupied() && otherField.getPiece().getOwner() != this.getOwner()) {
                    moves.add(new Move(position, otherPosition, this, otherField.getPiece(), otherPosition));
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
                                Position newPawnPosition = new Position(position.getRow() + diff, position.getColumn() + diffX);
                                moves.add(new Move(position, newPawnPosition, this, piece, otherPosition));
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    @Override
    public void makeMoveBackend(Move move) {
        ChessBoard board = this.getField().getBoard();

        // En passant
        int diffX = move.getMovedPieceEndPosition().getColumn() - move.getMovedPieceStartPosition().getColumn();
        if (board.getField(move.getMovedPieceEndPosition()).isFree() && diffX != 0) {
            Position positionToRemove = move.getMovedPieceStartPosition().transform(0, diffX);
            board.getField(positionToRemove).setPiece(null);
        }

        super.makeMoveBackend(move);

        if (this.isBeingPromoted(move)) {
            this.handlePawnPromotion(move);
        }
    }

    private void wypisz(Position pozycja) {
        System.out.println(String.format("(%d, %d)", pozycja.getRow(), pozycja.getColumn()));
    }

    @Override
    public void takeBackMoveBackend(Move move) {
        System.out.println();
        wypisz(move.getMovedPieceStartPosition());
        wypisz(move.getMovedPieceEndPosition());
        wypisz(move.getRemovedPiecePosition());
        System.out.println();


        ChessBoard board = this.getField().getBoard();

        // Detect en passant
        Field removedPieceField = board.getField(move.getRemovedPiecePosition());

        if (removedPieceField.isFree()) {
            removedPieceField.setPiece(move.getRemovedPiece());
        }

        super.takeBackMoveBackend(move);
    }

    public boolean isBeingPromoted(Move move) {
        int lastRow = this.getOwner() == Player.WHITE ? 0 : 7;
        return move.getMovedPieceEndPosition().getRow() == lastRow;
    }

    public void handlePawnPromotion(Move move) {
        Field endField = this.getField().getBoard().getField(move.getMovedPieceEndPosition());

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
}
