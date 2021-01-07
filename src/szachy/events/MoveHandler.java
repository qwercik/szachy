package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.GameWindow;
import szachy.controls.ControlPanel;
import szachy.engine.*;
import szachy.pieces.ChessPiece;

public class MoveHandler extends Event {
    public MoveHandler(Field end) {
        super(TYPE);
        this.end = end;
    }

    public void handle(GameWindow app) {
        GameState state = app.getGameState();
        ControlPanel controlPanel = app.getControlPanel();
        ChessBoard board = state.getBoard();

        if (this.end.getState().getType() == FieldState.Type.STARTING_POINT) {
            board.reset();
        } else if (this.end.getState().getType() == FieldState.Type.DESTINATION) {
            Field field = board.getStartingPoint();
            if (field != null) {
                state.makeMove(this.end.getPosition());

                controlPanel.update(state);
                board.reset();
            }
        } else {
            board.reset();

            if (this.end.isOccupied()) {
                this.end.setState(this.end.getState().withType(FieldState.Type.STARTING_POINT));
                ChessPiece piece = this.end.getPiece();
                if (piece.getOwner() == state.getCurrentPlayer()) {
                    for (Position destination : piece.getAllPossibleDestinations()) {
                        Field field = board.getField(destination);
                        field.setState(field.getState().withType(FieldState.Type.DESTINATION));
                    }
                }
            }
        }

        state.update();
    }



    public static final EventType<MoveHandler> TYPE = new EventType<>(Event.ANY, "MAKE_MOVE");
    private final Field end;
}
