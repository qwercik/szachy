package szachy;

import javafx.scene.image.Image;

import java.util.LinkedList;

public abstract class ChessPiece {
    protected Player owner;
    protected Field field;


    public ChessPiece(Player owner) {
        this.owner = owner;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return this.field;
    }

    protected String getIconsDirectory() {
        String basePath = "/assets/pieces";
        if (this.owner.getType() == Player.Type.WHITE) {
            return String.format("%s/white", basePath);
        } else {
            return String.format("%s/black", basePath);
        }
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
}
