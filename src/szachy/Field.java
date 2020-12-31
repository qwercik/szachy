package szachy;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field extends Button {
    private static final int SIZE = 50;

    Field() {
        ImageView image = new ImageView(new Image("/assets/pieces/white/pawn.svg"));
        image.setFitHeight(SIZE);
        image.setFitWidth(SIZE);

        this.setPrefSize(SIZE, SIZE);
        this.setGraphic(image);
    }
}
