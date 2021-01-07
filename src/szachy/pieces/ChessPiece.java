package szachy.pieces;

import javafx.scene.image.Image;
import szachy.engine.*;

import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class ChessPiece {
    public enum Type {
        KING,
        QUEEN,
        ROOK,
        KNIGHT,
        BISHOP,
        PAWN
    }

    public ChessPiece(Player owner) {
        this.owner = owner;
    }

    public abstract Type getType();

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return this.field;
    }

    public Player getOwner() {
        return this.owner;
    }

    public boolean isOwnedByCurrentPlayer() {
        return this.getOwner() == this.getField().getBoard().getGameState().getCurrentPlayer();
    }

    protected String getIconsDirectory() {
        return String.format("%s/%s", "/assets/pieces", this.owner == Player.WHITE ? "white" : "black");
    }

    protected String obtainIconPath(String filename) {
        return String.format("" +
                "%s/%s.svg",
                this.getIconsDirectory(),
                filename
        );
    }

    public Move makeMove(Position endPosition) {
        Move move = this.makeMoveBackend(endPosition);
        this.movesHistory.addLast(move);

        return move;
    }

    public void takeBackMove(Move move) {
        this.takeBackMoveBackend(move);
        this.movesHistory.removeLast();
    }

    public boolean hasAlreadyMoved() {
        return !this.movesHistory.isEmpty();
    }

    public LinkedList<Move> getMovesHistory() {
        return this.movesHistory;
    }

    public LinkedList<Position> getAllPossibleDestinations() {
        GameState state = this.getField().getBoard().getGameState();

        return this.getAllPossibleDestinationsBackend()
                .stream()
                //.filter(state::verifyMoveForCheck)
                .collect(Collectors.toCollection(LinkedList::new));
    }




    // Default implementations of make move/take back
    // For specific cases (as castling, for example) pieces can override them

    protected Move makeMoveBackend(Position endPosition) {
        ChessBoard board = this.field.getBoard();
        Field endField = board.getField(endPosition);

        Move move = new Move(
                this.field.getPosition(),
                endPosition,
                this,
                endField.getPiece(),
                endPosition
        );

        this.field.setPiece(null);
        endField.setPiece(this);

        return move;
    }

    protected void takeBackMoveBackend(Move move) {
        ChessBoard board = this.field.getBoard();
        Field startField = board.getField(move.getMovedPieceStartPosition());
        Field endField = board.getField(move.getMovedPieceEndPosition());
        Field removedPieceField = board.getField(move.getRemovedPiecePosition());

        endField.setPiece(null);
        removedPieceField.setPiece(move.getRemovedPiece());
        startField.setPiece(move.getMovedPiece());
    }

    public abstract LinkedList<Position> getAllPossibleDestinationsBackend();
    public abstract Image getIcon();

    protected Player owner;
    protected Field field;

    private final LinkedList<Move> movesHistory = new LinkedList<>();
}
