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

    public Field(Type type, Position position, ChessBoard board) {
        this.type = type;
        this.position = position;
        this.board = board;

        this.setPrefSize(Dimensions.FIELD_SIZE, Dimensions.FIELD_SIZE);
        this.setPadding(Insets.EMPTY);
        this.setBorder(Border.EMPTY);

        this.getStylesheets().add("/assets/css/field.css");
        this.update();
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;

        if (piece == null) {
            this.setGraphic(null);
        } else {
            ImageView image = new ImageView(this.piece.getIcon());
            piece.setField(this);
            image.setFitHeight(Dimensions.FIELD_SIZE);
            image.setFitWidth(Dimensions.FIELD_SIZE);

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

    public void setState(FieldState state) {
        this.state = state;
    }

    public FieldState getState() {
        return this.state;
    }

    public void update() {
        this.getStyleClass().clear();
        this.getStyleClass().add("field");
        this.getStyleClass().add(this.type.getCssClass());
        if (this.state.getCssClass() != null) {
            this.getStyleClass().add(this.state.getCssClass());
        }

        if (this.state.getType() == FieldState.Type.STARTING_POINT) {
            this.getBoard().setStartingPoint(this);
        }
    }


    private final Type type;
    private FieldState state = new FieldState();

    private final Position position;
    private final ChessBoard board;
    private ChessPiece piece;
}
