package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.engine.GameState;

public class GiveUp extends Button implements Control {
    public GiveUp() {
        super(title);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setOnAction(event -> this.fireEvent(new szachy.events.GiveUp()));
    }

    @Override
    public void update(GameState state) {

    }

    private static final String title = "Poddaj się";
}
