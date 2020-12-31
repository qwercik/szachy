package szachy;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class ChessBoard extends GridPane {
    private static final int SIZE = 8;

    private Field[][] fields = new Field[SIZE][SIZE];

    ChessBoard() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Field.Type fieldType = (x + y) % 2 == 0 ? Field.Type.WHITE : Field.Type.BLACK;
                Field field = new Field(fieldType, new Position(y, x), this);
                field.setOnAction(event -> {
                    if (field.getPiece() != null) {
                        LinkedList<Move> validMoves = field.getPiece().getAllPossibleMoves();
                        for (Move move : validMoves) {
                            Position position = move.getEnd();
                            Field other = this.getField(position);
                            other.toggleHighlight();
                        }
                    }
                });

                this.fields[y][x] = field;
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

