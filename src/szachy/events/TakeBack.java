package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.engine.GameState;
import szachy.GameWindow;

public class TakeBack extends Event {
    public TakeBack() {
        super(TYPE);
    }

    public void handle(GameWindow app) {
        GameState state = app.getGameState();
        if (state.canTakeBack()) {
            state.takeBackMove();
        }
    }

    public static final EventType<TakeBack> TYPE = new EventType<>(Event.ANY, "TAKE_BACK");
}
