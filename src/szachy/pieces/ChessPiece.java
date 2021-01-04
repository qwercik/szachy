package szachy.pieces;

import javafx.scene.image.Image;
import szachy.engine.ChessBoard;
import szachy.engine.Field;
import szachy.engine.Move;
import szachy.engine.Player;

import java.util.LinkedList;

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

    public void makeMove(Move move) {
        this.makeMoveBackend(move);
        this.movesHistory.addLast(move);
    }

    public void takeBackMove(Move move) {
        this.takeBackMoveBackend(move);
        this.movesHistory.removeLast();
    }

    public boolean hasAlreadyMoved() {
        return !this.movesHistory.isEmpty();
    }


    public abstract Image getIcon();
    public abstract LinkedList<Move> getAllPossibleMoves();


    // Default implementations of make move/take back
    // For specific cases (as castling, for example) pieces can override them

    protected void makeMoveBackend(Move move) {
        ChessBoard board = this.field.getBoard();
        Field endField = board.getField(move.getEnd());
        this.field.setPiece(null);
        endField.setPiece(this);
    }

    protected void takeBackMoveBackend(Move move) {
        ChessBoard board = this.field.getBoard();
        Field startField = board.getField(move.getStart());
        Field endField = board.getField(move.getEnd());

        endField.setPiece(move.getEndPiece());
        startField.setPiece(this);
    }


    protected Player owner;
    protected Field field;

    private final LinkedList<Move> movesHistory = new LinkedList<>();
}
