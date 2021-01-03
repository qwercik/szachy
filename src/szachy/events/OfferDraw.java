package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.GameWindow;

public class OfferDraw extends Event {
    public OfferDraw() {
        super(TYPE);
    }

    public void handle(GameWindow app) {
        app.showResultsAndAskForOneMoreGame(null);
    }

    public static final EventType<OfferDraw> TYPE = new EventType<>(Event.ANY, "OFFER_DRAW");
}
