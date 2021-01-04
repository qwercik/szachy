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
            board.setAllFieldsStateDefault(false);
        } else if (this.end.getState().getType() == FieldState.Type.DESTINATION) {
            Field field = board.getStartingPoint();
            if (field != null) {
                state.makeMove(new Move(
                        field.getPosition(),
                        field.getPiece(),
                        this.end.getPosition(),
                        this.end.getPiece()
                ));

                controlPanel.update(state);
                board.setAllFieldsStateDefault(true);
            }
        } else {
            board.setAllFieldsStateDefault(false);

            if (this.end.isOccupied()) {
                this.end.setState(this.end.getState().withType(FieldState.Type.STARTING_POINT));
                ChessPiece piece = this.end.getPiece();
                if (piece.getOwner() == state.getCurrentPlayer()) {
                    for (Move move : piece.getAllPossibleMoves()) {
                        Field field = board.getField(move.getEnd());
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
