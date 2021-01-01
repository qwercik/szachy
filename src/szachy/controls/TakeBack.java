package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.GameState;

public class TakeBack extends Button {
    private static final String title = "Cofnij ruch";

    public TakeBack(GameState state) {
        super(title);
        this.setPadding(new Insets(10, 10, 10, 10));
    }
}
