package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;

public class King extends ChessPiece {
    @Override
    public Image getIcon() {
        return new Image("/assets/pieces/white/king.svg");
    }
}
