package szachy.engine;

public class Position {
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

    private boolean coordinateValid(int coordinate) {
        return coordinate >= 0 && coordinate < ChessBoard.SIZE;
    }

    public boolean equals(Position other) {
        return this.row == other.row && this.column == other.column;
    }

    private int row;
    private int column;
}
