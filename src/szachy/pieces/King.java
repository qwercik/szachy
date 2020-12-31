package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Move;
import szachy.Player;

import java.util.LinkedList;

public class King extends ChessPiece {
    public King(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("king"));
    }

    @Override
    public LinkedList<Move> getAllPossibleMoves() {
        return new LinkedList<Move>();
    }
}
