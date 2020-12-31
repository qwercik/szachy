package szachy;

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

    private int row;
    private int column;
}
