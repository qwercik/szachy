package szachy;

public class Position {
    private static final int BOARD_SIZE = 8;

    public Position(int row, int column) {
        this.setRow(row);
        this.setColumn(column);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Position transform(int diffY, int diffX) {
        int newRow = this.getRow() + diffY;
        int newColumn = this.getColumn() + diffX;

        if (coordinateValid(newRow) && coordinateValid(newColumn)) {
            return new Position(newRow, newColumn);
        }

        return null;
    }

    public Position mirror() {
        return new Position(
                BOARD_SIZE - 1 - this.getRow(),
                this.getColumn()
        );
    }

    private boolean coordinateValid(int coordinate) {
        return coordinate >= 0 && coordinate < BOARD_SIZE;
    }

    private int row;
    private int column;
}
