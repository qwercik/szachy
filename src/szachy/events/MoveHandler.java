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
            Field field = board.getActiveField();
            if (field != null) {
                state.makeMove(new Move(
                        field.getPosition(),
                        field.getPiece(),
                        this.end.getPosition(),
                        this.end.getPiece()
                ));

                controlPanel.update(state);
                board.setAllFieldsStateDefault(true);
                this.handleCheckIfOccurred(board, state.getCurrentPlayer());
            }
        } else {
            board.setAllFieldsStateDefault(false);

            if (this.end.isOccupied()) {
                this.end.updateState(this.end.getState().withType(FieldState.Type.STARTING_POINT));
                ChessPiece piece = this.end.getPiece();
                if (piece.getOwner() == state.getCurrentPlayer()) {
                    for (Move move : piece.getAllPossibleMoves()) {
                        Field field = board.getField(move.getEnd());
                        field.updateState(field.getState().withType(FieldState.Type.DESTINATION));
                    }
                }
            }
        }
    }

    private void handleCheckIfOccurred(ChessBoard board, Player potentiallyChecked) {
        for (Field[] row : board.getAllFields()) {
            for (Field field : row) {
                ChessPiece piece = field.getPiece();
                if (piece != null && piece.getType() == ChessPiece.Type.KING && piece.getOwner() == potentiallyChecked) {
                    Player attacker = potentiallyChecked.opposite();
                    if (board.isAttacked(field.getPosition(), attacker)) {
                        field.updateState(field.getState().withCheck(true));
                    }

                    return;
                }
            }
        }
    }

    public static final EventType<MoveHandler> TYPE = new EventType<>(Event.ANY, "MAKE_MOVE");
    private final Field end;
}
