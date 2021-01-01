package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.GameState;

public class OfferDraw extends Button {
    private static final String title = "Zaproponuj remis";

    public OfferDraw(GameState state) {
        super(title);
        this.setPadding(new Insets(10, 10, 10, 10));
    }
}
