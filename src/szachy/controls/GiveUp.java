package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import szachy.GameState;

public class GiveUp extends Button {
    private static final String title = "Poddaj się";

    public GiveUp(GameState state) {
        super(title);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setOnAction(event -> {
            state.endGameWithWin(state.getPlayer().toggle());
        });
    }
}
