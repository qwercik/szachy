package szachy.pieces;

import javafx.scene.image.Image;
import szachy.ChessPiece;

public class Pawn extends ChessPiece {
    @Override
    public Image getIcon() {
        return new Image("/assets/pieces/white/pawn.svg");
    }
}
