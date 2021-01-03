package szachy.events;

import javafx.event.Event;
import javafx.event.EventType;
import szachy.GameWindow;
import szachy.controls.ControlPanel;
import szachy.engine.ChessBoard;
import szachy.engine.Field;
import szachy.engine.GameState;
import szachy.engine.Move;
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

        if (this.end.getState() == Field.State.STARTING_POINT) {
            board.setAllFieldsStateDefault();
        } else if (this.end.getState() == Field.State.DESTINATION) {
            Field field = board.getActiveField();
            if (field != null) {
                state.makeMove(new Move(
                        field.getPosition(),
                        field.getPiece(),
                        this.end.getPosition(),
                        this.end.getPiece()
                ));

                controlPanel.update(state);
                board.setAllFieldsStateDefault();
            }
        } else {
            board.setAllFieldsStateDefault();

            if (this.end.isOccupied()) {
                this.end.setState(Field.State.STARTING_POINT);
                ChessPiece piece = this.end.getPiece();
                for (Move move : piece.getAllPossibleMoves()) {
                    board.getField(move.getEnd()).setState(Field.State.DESTINATION);
                }
            }
        }
    }

    public static final EventType<MoveHandler> TYPE = new EventType<>(Event.ANY, "MAKE_MOVE");
    private final Field end;
}
