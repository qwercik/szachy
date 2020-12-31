package szachy;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ChessBoard extends GridPane {
    private static final int SIZE = 8;

    private Field[][] fields = new Field[SIZE][SIZE];

    ChessBoard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.fields[y][x] = new Field((x + y) % 2 == 0 ? Field.Type.WHITE : Field.Type.BLACK);
                this.add(this.fields[y][x], x, y);
            }
        }
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
}

