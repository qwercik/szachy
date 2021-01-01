package szachy.controls;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import szachy.GameState;
import szachy.Player;

public class TurnInfo extends Label {
    private static final String WHITE_TURN = "Ruch białych";
    private static final String BLACK_TURN = "Ruch czarnych";
    private GameState state;

    public TurnInfo(GameState state) {
        super(WHITE_TURN);
        this.state = state;
        this.setAlignment(Pos.CENTER);
    }

    public void update() {
        this.setText(this.state.getPlayer() == Player.WHITE ? WHITE_TURN : BLACK_TURN);
    }
}
