package szachy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class ChessBoard extends GridPane {
    private static final int SIZE = 8;
    private Field[][] fields = new Field[SIZE][SIZE];

    ChessBoard() {
        this.initChessboardAndLabels();
    }

    public Field getField(Position position) {
        return this.fields[position.getRow()][position.getColumn()];
    }

    public void setField(Position position, Field field) {
        this.fields[position.getRow()][position.getColumn()] = field;
    }

    public void setPiece(Position position, ChessPiece piece) {
        Field field = this.getField(position);
        field.setPiece(piece);
        this.setField(position, field);
    }

    public Position mirrorPosition(Position position) {
        return new Position(
                SIZE - 1 - position.getRow(),
                position.getColumn()
        );
    }

    private void initChessboardAndLabels() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Field.Type fieldType = (x + y) % 2 == 0 ? Field.Type.WHITE : Field.Type.BLACK;
                this.fields[y][x] = new Field(fieldType, new Position(y, x), this);
                this.add(this.fields[y][x], x, y);
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
}

