package szachy;

import szachy.pieces.Queen;

public class GameState {
    private Player[] players;
    private ChessBoard board;

    public GameState() {
        this.players = new Player[] {
                new Player(Player.Type.WHITE),
                new Player(Player.Type.BLACK)
        };

        this.board = new ChessBoard();

        Position position = new Position(4, 4);
        ChessPiece piece = new Queen(this.players[0]);
        Field field = this.board.getField(position);
        field.setPiece(piece);
        this.board.setField(position, field);
    }

    public ChessBoard getChessBoard() {
        return this.board;
    }
}
