package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;

public class Bishop extends ChessPiece {
    @Override
    public Image getIcon() {
        return new Image("/assets/pieces/white/bishop.svg");
    }
}
