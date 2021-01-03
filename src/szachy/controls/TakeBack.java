package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.engine.GameState;

public class TakeBack extends Button implements Control {
    public TakeBack()  {
        super(title);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setDisable(true);


        this.setOnAction(event -> this.fireEvent(new szachy.events.TakeBack()));
    }

    public void update(GameState state) {
        this.setDisable(!state.canTakeBack());
    }

    private static final String title = "Cofnij ruch";
}
