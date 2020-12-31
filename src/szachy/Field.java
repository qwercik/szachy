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

    private static final String WHITE_STYLE = "-fx-background-color: #dbcbc3";
    private static final String WHITE_HOVER_STYLE = "-fx-background-color: #ecdcd4";
    private static final String BLACK_STYLE = "-fx-background-color: #5f2f15";
    private static final String BLACK_HOVER_STYLE = "-fx-background-color: #704026";

    private static final int SIZE = 75;
    private ChessPiece piece;
    private Type type;

    Field(Type type) {
        this.type = type;

        this.setStyle(type == Type.WHITE ? WHITE_STYLE : BLACK_STYLE);
        this.setPrefSize(SIZE, SIZE);
        this.setPadding(Insets.EMPTY);
        this.setBorder(Border.EMPTY);

        this.setOnMouseEntered(event -> {
            if (this.type == Type.WHITE) {
                this.setStyle(WHITE_HOVER_STYLE);
            } else {
                this.setStyle(BLACK_HOVER_STYLE);
            }
        });

        this.setOnMouseExited(event -> {
            if (this.type == Type.WHITE) {
                this.setStyle(WHITE_STYLE);
            } else {
                this.setStyle(BLACK_STYLE);
            }
        });
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
