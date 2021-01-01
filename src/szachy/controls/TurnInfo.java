package szachy.controls;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import szachy.GameState;
import szachy.Player;

public class TurnInfo extends Label {
    private static final String WHITE_TURN = "Ruch białych";
    private static final String BLACK_TURN = "Ruch czarnych";
    private Player player;

    public TurnInfo(GameState state) {
        super(WHITE_TURN);
        this.player = state.getPlayer();
        this.setAlignment(Pos.CENTER);
    }

    public void toggle() {
        this.setText(this.player.toggle() == Player.WHITE ? BLACK_TURN : WHITE_TURN);
    }
}
