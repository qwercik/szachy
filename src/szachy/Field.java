package szachy;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field extends Button {
    private static final int SIZE = 50;
    private ChessPiece piece;

    Field() {
        this.setPrefSize(SIZE, SIZE);
    }

    void setPiece(ChessPiece piece) {
        this.piece = piece;

        ImageView image = new ImageView(this.piece.getIcon());
        image.setFitHeight(SIZE);
        image.setFitWidth(SIZE);

        this.setGraphic(image);
    }

    ChessPiece getPiece() {
        return this.piece;
    }
}
