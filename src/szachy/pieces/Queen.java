package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;

public class Queen extends ChessPiece {
    @Override
    public Image getIcon() {
        return new Image("/assets/pieces/white/queen.svg");
    }
}
