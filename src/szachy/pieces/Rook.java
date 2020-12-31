package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Player;

public class Rook extends ChessPiece {
    public Rook(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("rook"));
    }
}
