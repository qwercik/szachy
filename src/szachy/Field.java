package szachy;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

public class Field extends Button {
    public enum Type {
        WHITE,
        BLACK
    }

    private static final int SIZE = 75;
    private ChessPiece piece;
    private Type type;

    Field(Type type) {
        this.type = type;

        this.getStylesheets().add("/assets/css/field.css");
        this.getStyleClass().add("field");
        this.getStyleClass().add(type == Type.WHITE ? "field--white" : "field--black");

        this.setPrefSize(SIZE, SIZE);
        this.setPadding(Insets.EMPTY);
        this.setBorder(Border.EMPTY);
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
