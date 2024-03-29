package szachy.engine;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import szachy.events.MoveHandler;

public class ChessBoard extends GridPane {
    public static final int SIZE = 8;

    public ChessBoard(GameState gameState) {
        this.gameState = gameState;

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Field.Type fieldType = (x + y) % 2 == 0 ? Field.Type.WHITE : Field.Type.BLACK;
                Field field = new Field(fieldType, new Position(y, x), this);
                field.setOnAction(event -> this.fireEvent(new MoveHandler(field)));

                this.add(field, x, y);
                this.fields[y][x] = field;
            }
        }

        for (int y = 0; y < SIZE; y++) {
            Label label = new Label(String.format("%d", SIZE - y));
            label.setPadding(new Insets(0, 5, 0, 5));
            label.setMaxWidth(20);
            label.setMaxHeight(75);
            label.setAlignment(Pos.CENTER);
            this.add(label, SIZE, y);
        }

        for (int x = 0; x < SIZE; x++) {
            Label label = new Label(String.format("%c", 'A' + x));
            label.setPadding(new Insets(5, 0, 5, 0));
            label.setMaxWidth(75);
            label.setMaxHeight(20);
            AnchorPane.setLeftAnchor(label, 0.0);
            AnchorPane.setRightAnchor(label, 0.0);
            label.setAlignment(Pos.CENTER);
            this.add(label, x, SIZE);
        }
    }

    public void reset() {
        for (Field[] row : this.fields) {
            for (Field field : row) {
                field.setState(new FieldState(FieldState.Type.DEFAULT, false));
            }
        }
    }

    public Field[][] getAllFields() {
        return this.fields;
    }

    public Field getField(Position position) {
        return this.fields[position.getRow()][position.getColumn()];
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setStartingPoint(Field field) {
        this.startingPoint = field;
    }

    public Field getStartingPoint() {
        return this.startingPoint;
    }

    public void update() {
        for (Field[] row : this.getAllFields()) {
            for (Field field : row) {
                field.update();
            }
        }
    }

    private final GameState gameState;
    private final Field[][] fields = new Field[SIZE][SIZE];
    private Field startingPoint;
}

