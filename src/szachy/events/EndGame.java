package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.GameWindow;
import szachy.engine.Player;

public class EndGame extends Event {
    public EndGame(Player winner) {
        super(TYPE);
        this.winner = winner;
    }

    public void handle(GameWindow app) {
        app.showResultsAndAskForOneMoreGame(this.winner);
    }

    public static final EventType<EndGame> TYPE = new EventType<>(Event.ANY, "END_GAME");

    Player winner;
}
