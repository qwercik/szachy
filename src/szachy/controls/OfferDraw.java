package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.GameState;

public class OfferDraw extends Button {
    private static final String TEXT = "Zakończ grę remisem";

    public OfferDraw(GameState state) {
        super(TEXT);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setOnAction(event -> state.endGameWithDraw());
    }
}
