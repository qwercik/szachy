package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;
import szachy.Player;

public class King extends ChessPiece {
    public King(Player player) {
        super(player);
    }

    @Override
    public Image getIcon() {
        return new Image(this.obtainIconPath("king"));
    }
}
