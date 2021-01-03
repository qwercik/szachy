package szachy.engine;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import szachy.Dimensions;
import szachy.pieces.ChessPiece;

public class Field extends Button {
    public enum Type {
        WHITE,
        BLACK;

        public String getCssClass() {
            if (this == WHITE) {
                return "field--white";
            } else {
                return "field--black";
            }
        }
    }

    public enum State {
        DEFAULT,
        STARTING_POINT,
        KING_UNDER_CHECK,
        ATTACKED_FIELD;

        public boolean isStartingPoint() {
            return this == STARTING_POINT;
        }

        public boolean isDestination() {
            return this == KING_UNDER_CHECK || this == ATTACKED_FIELD;
        }

        public String getCssClass() {
            if (this == STARTING_POINT) {
                return "field--starting-point";
            } else if (this == ATTACKED_FIELD) {
                return "field--destination";
            } else if (this == KING_UNDER_CHECK) {
                return "field--king-under-check";
            } else {
                return null;
            }
        }
    }

    public Field(Type type, Position position, ChessBoard board) {
        this.position = position;
        this.board = board;

        this.getStylesheets().add("/assets/css/field.css");
        this.getStyleClass().add("field");
        this.getStyleClass().add(type.getCssClass());

        this.setPrefSize(Dimensions.FIELD, Dimensions.FIELD);
        this.setPadding(Insets.EMPTY);
        this.setBorder(Border.EMPTY);
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;

        if (piece == null) {
            this.setGraphic(null);
        } else {
            ImageView image = new ImageView(this.piece.getIcon());
            piece.setField(this);
            image.setFitHeight(Dimensions.FIELD);
            image.setFitWidth(Dimensions.FIELD);

            this.setGraphic(image);
        }
    }

    public ChessPiece getPiece() {
        return this.piece;
    }

    public Position getPosition() {
        return this.position;
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public boolean isFree() {
        return this.piece == null;
    }

    public boolean isOccupied() {
        return this.piece != null;
    }

    public void setState(State newState) {
        String cssClassToRemove = this.state.getCssClass();
        if (cssClassToRemove != null) {
            this.getStyleClass().remove(cssClassToRemove);
        }

        if (this.state == State.STARTING_POINT) {
            this.getBoard().setActiveField(null);
        }

        this.state = newState;
        String cssClassToAdd = this.state.getCssClass();
        if (cssClassToAdd != null) {
            this.getStyleClass().add(cssClassToAdd);
        }

        if (this.state == State.STARTING_POINT) {
            this.getBoard().setActiveField(this);
        }
    }

    public State getState() {
        return this.state;
    }


    private State state = State.DEFAULT;

    private final Position position;
    private final ChessBoard board;
    private ChessPiece piece;
}
