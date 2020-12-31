package szachy;

import szachy.pieces.*;

public class GameState {
    private Player[] players;
    private ChessBoard board;

    public GameState() {
        this.initPlayers();
        this.initChessboard();
    }

    public ChessBoard getChessBoard() {
        return this.board;
    }

    private void initPlayers() {
        this.players = new Player[] {
                new Player(Player.Type.WHITE),
                new Player(Player.Type.BLACK)
        };
    }

    private void initChessboard() {
        this.board = new ChessBoard();

        this.board.setPiece(new Position(0, 0), new Rook(this.players[1]));
        this.board.setPiece(new Position(0, 1), new Knight(this.players[1]));
        this.board.setPiece(new Position(0, 2), new Bishop(this.players[1]));
        this.board.setPiece(new Position(0, 3), new Queen(this.players[1]));
        this.board.setPiece(new Position(0, 4), new King(this.players[1]));
        this.board.setPiece(new Position(0, 5), new Bishop(this.players[1]));
        this.board.setPiece(new Position(0, 6), new Knight(this.players[1]));
        this.board.setPiece(new Position(0, 7), new Rook(this.players[1]));
        for (int i = 0; i < 8; i++) {
            this.board.setPiece(new Position(1, i), new Pawn(this.players[1]));
        }


        this.board.setPiece(new Position(7, 0), new Rook(this.players[0]));
        this.board.setPiece(new Position(7, 1), new Knight(this.players[0]));
        this.board.setPiece(new Position(7, 2), new Bishop(this.players[0]));
        this.board.setPiece(new Position(7, 3), new Queen(this.players[0]));
        this.board.setPiece(new Position(7, 4), new King(this.players[0]));
        this.board.setPiece(new Position(7, 5), new Bishop(this.players[0]));
        this.board.setPiece(new Position(7, 6), new Knight(this.players[0]));
        this.board.setPiece(new Position(7, 7), new Rook(this.players[0]));
        for (int i = 0; i < 8; i++) {
            this.board.setPiece(new Position(6, i), new Pawn(this.players[0]));
        }
    }
}
