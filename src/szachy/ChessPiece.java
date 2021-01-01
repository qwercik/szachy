package szachy;

import javafx.scene.image.Image;

import java.util.LinkedList;

public abstract class ChessPiece {
    protected Player owner;
    protected Field field;
    protected boolean alreadyMoved = false;

    public ChessPiece(Player owner) {
        this.owner = owner;
    }

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
        return this.getOwner() == this.getField().getBoard().getState().getPlayer();
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

    public boolean isMovePossible(Move move) {
        return this.getAllPossibleMoves().contains(move);
    }

    public abstract Image getIcon();
    public abstract LinkedList<Move> getAllPossibleMoves();
    public void makeMove(Move move) {
        this.alreadyMoved = true;

        ChessBoard board = this.getField().getBoard();
        Field startField = board.getField(move.getStart());
        Field endField = board.getField(move.getEnd());
        endField.setPiece(startField.getPiece());
        startField.setPiece(null);
    }
}
