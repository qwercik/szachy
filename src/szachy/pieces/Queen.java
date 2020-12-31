package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Player;

public class Queen extends ChessPiece {
    public Queen(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("queen"));
    }
}
