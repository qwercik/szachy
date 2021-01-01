package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import szachy.GameState;

public class TakeBack extends Button {
    private static final String title = "Cofnij ruch";
    private GameState state;

    public TakeBack(GameState state) {
        super(title);
        this.state = state;
        this.setPadding(new Insets(10, 10, 10, 10));
        this.update();

        this.setOnAction(event -> {
            state.takeBackMove();
        });
    }

    public void update() {
        this.setDisable(!state.canTakeBack());
    }
}
