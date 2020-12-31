package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Player;

public class Bishop extends ChessPiece {
    public Bishop(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("bishop"));
    }
}
