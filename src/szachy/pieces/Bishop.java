package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Move;
import szachy.Player;

import java.util.LinkedList;

public class Bishop extends ChessPiece {
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("bishop"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        return new LinkedList<Move>();
    }
}
