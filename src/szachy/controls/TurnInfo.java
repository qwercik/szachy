package szachy.controls;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import szachy.engine.GameState;
import szachy.engine.Player;

public class TurnInfo extends Label implements Control {
    private static final String WHITE_TURN = "Ruch białych";
    private static final String BLACK_TURN = "Ruch czarnych";

    public TurnInfo() {
        super(WHITE_TURN);
        this.setAlignment(Pos.CENTER);
    }

    public void update(GameState state) {
        this.setText(state.getCurrentPlayer() == Player.WHITE ? WHITE_TURN : BLACK_TURN);
    }
}
