package szachy;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ChessBoard extends GridPane {
    private static final int SIZE = 8;
    private static final String WHITE_FIELDS_STYLE = "-fx-background-color: #dbcbc3";
    private static final String BLACK_FIELDS_STYLE = "-fx-background-color: #5f2f15";

    private Field[][] fields = new Field[SIZE][SIZE];

    ChessBoard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.fields[y][x] = new Field();
                if ((x + y) % 2 == 0) {
                    this.fields[y][x].setStyle(WHITE_FIELDS_STYLE);
                } else {
                    this.fields[y][x].setStyle(BLACK_FIELDS_STYLE);
                }

                this.add(this.fields[y][x], y, x);
            }
        }
    }

    public Field getField(Position position) {
        return this.fields[position.getRow()][position.getColumn()];
    }

    public void setField(Position position, Field field) {
        this.fields[position.getRow()][position.getColumn()] = field;
    }
}

