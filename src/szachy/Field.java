package szachy;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class Field extends Button {
    private static final int SIZE = 50;
    private ChessPiece piece;

    Field() {
        this.setPrefSize(SIZE, SIZE);
        this.setPadding(Insets.EMPTY);
        this.setBorder(Border.EMPTY);
        this.setStyle("-fx-background-radius: 0");
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
