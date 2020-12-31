package szachy;

import javafx.scene.layout.GridPane;
import szachy.pieces.King;

public class ChessBoard extends GridPane {
    private static final int SIZE = 8;
    private Field[][] fields = new Field[SIZE][SIZE];

    ChessBoard() {
        this.setMaxSize(400, 400);

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                this.fields[y][x] = new Field();
                this.fields[y][x].setPiece(new King());
                this.add(this.fields[y][x], y, x);
            }
        }
    }
}
