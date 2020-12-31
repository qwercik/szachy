package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Player;

public class Pawn extends ChessPiece {
    public Pawn(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("pawn"));
    }
}
