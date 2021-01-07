package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.controls.ControlPanel;
import szachy.engine.GameState;
import szachy.GameWindow;

public class TakeBack extends Event {
    public TakeBack() {
        super(TYPE);
    }

    public void handle(GameWindow app) {
        GameState state = app.getGameState();
        ControlPanel panel = app.getControlPanel();
        if (state.canTakeBack()) {
            state.takeBackMove();
            panel.update(state);
            state.getBoard().reset();
            state.update();
        }
    }

    public static final EventType<TakeBack> TYPE = new EventType<>(Event.ANY, "TAKE_BACK");
}
