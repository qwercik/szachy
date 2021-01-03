package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.GameWindow;

public class GiveUp extends Event {
    public GiveUp() {
        super(TYPE);
    }

    public void handle(GameWindow app) {
        app.showResultsAndAskForOneMoreGame(app.getGameState().getCurrentPlayer().opposite());
    }

    public static final EventType<GiveUp> TYPE = new EventType<>(Event.ANY, "GIVE_UP");
}
