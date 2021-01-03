package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.GameWindow;

public class EndGame extends Event {
    public EndGame() {
        super(TYPE);
    }

    public void handle(GameWindow app) {
        app.showResultsAndAskForOneMoreGame(app.getGameState().getCurrentPlayer());
    }

    public static final EventType<EndGame> TYPE = new EventType<>(Event.ANY, "END_GAME");
}
