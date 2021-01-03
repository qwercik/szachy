package szachy.events;

import com.sun.media.jfxmediaimpl.platform.Platform;
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

        if (this.end.getState().isStartingPoint()) {
            board.setAllFieldsStateDefault();
        } else if (this.end.getState().isDestination()) {
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
                this.handleCheckIfOccurred(board, state.getCurrentPlayer());
            }
        } else {
            board.setAllFieldsStateDefault();

            if (this.end.isOccupied()) {
                this.end.setState(Field.State.STARTING_POINT);
                ChessPiece piece = this.end.getPiece();
                for (Move move : piece.getAllPossibleMoves()) {
                    board.getField(move.getEnd()).setState(Field.State.ATTACKED_FIELD);
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
                        field.setState(Field.State.KING_UNDER_CHECK);
                    }

                    return;
                }
            }
        }
    }

    public static final EventType<MoveHandler> TYPE = new EventType<>(Event.ANY, "MAKE_MOVE");
    private final Field end;
}
