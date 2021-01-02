package szachy;

import javafx.collections.ObservableList;
import javafx.css.Style;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;

import java.util.LinkedList;

public class Field extends Button {
    public enum Type {
        WHITE,
        BLACK
    }

    private static final int SIZE = 75;
    private ChessPiece piece;
    private Position position;
    private ChessBoard board;
    private Type type;
    private boolean highlight = false;
    private boolean active = false;

    public Field(Type type, Position position, ChessBoard board) {
        this.type = type;
        this.position = position;
        this.board = board;

        this.getStylesheets().add("/assets/css/field.css");
        this.getStyleClass().add("field");
        this.getStyleClass().add(type == Type.WHITE ? "field--white" : "field--black");

        this.setPrefSize(SIZE, SIZE);
        this.setPadding(Insets.EMPTY);
        this.setBorder(Border.EMPTY);

        this.setOnAction(event -> {
            // Clicking two times at row on the same field should cause toggling it
            if (this.active) {
                this.getBoard().reset();
                return;
            }

            if (this.highlight) {
                GameState state = this.getBoard().getState();

                state.makeMove(new Move(
                        state.getActiveField().getPosition(),
                        this.getPosition(),
                        this.getPiece()
                ));
                this.getBoard().reset();
                return;
            }

            this.getBoard().reset();
            this.turnOnActive();

            if (!this.isFree()) {
                LinkedList<Move> validMoves = this.piece.getAllPossibleMoves();
                for (Move move : validMoves) {
                    Position endPosition = move.getEnd();
                    Field other = this.board.getField(endPosition);
                    other.toggleHighlight();
                }
            }
        });
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;

        if (piece == null) {
            this.setGraphic(null);
        } else {
            ImageView image = new ImageView(this.piece.getIcon());
            piece.setField(this);
            image.setFitHeight(SIZE);
            image.setFitWidth(SIZE);

            this.setGraphic(image);
        }
    }

    public ChessPiece getPiece() {
        return this.piece;
    }
    
    public ChessBoard getBoard() {
        return this.board;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isFree() {
        return this.piece == null;
    }

    public boolean isBusy() {
        return this.piece != null;
    }

    public void turnOnActive() {
        if (!this.active) {
            this.getStyleClass().add("field--active");
            this.active = true;
            this.getBoard().getState().setActiveField(this);
        }
    }

    public void turnOffActive() {
        if (this.active) {
            this.getStyleClass().remove("field--active");
            this.active = false;
            this.getBoard().getState().setActiveField(null);
        }
    }

    public void turnOffHighlight() {
        if (this.highlight) {
            this.toggleHighlight();
        }
    }

    public void toggleHighlight() {
        ObservableList<String> classes = this.getStyleClass();
        String className = "field--highlight";

        if (classes.contains(className)) {
            classes.remove(className);
        } else {
            classes.add(className);
        }

        this.highlight = !this.highlight;
    }
}
