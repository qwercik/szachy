package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.engine.GameState;

public class OfferDraw extends Button implements Control {
    public OfferDraw() {
        super(TEXT);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.setOnAction(event -> this.fireEvent(new szachy.events.OfferDraw()));
    }

    @Override
    public void update(GameState state) {

    }

    private static final String TEXT = "Zakończ grę remisem";
}
