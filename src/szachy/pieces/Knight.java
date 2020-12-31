package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;

public class Knight extends ChessPiece {
    @Override
    public Image getIcon() {
        return new Image("/assets/pieces/white/knight.svg");
    }
}
