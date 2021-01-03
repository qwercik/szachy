package szachy.engine;

import szachy.pieces.*;

import java.util.LinkedList;

public class GameState {
    public GameState() {
        this.board = new ChessBoard(this);

        for (Player player : new Player[] {Player.BLACK, Player.WHITE}) {
            this.board.getField(player.getRelativePosition(new Position(0, 0))).setPiece(new Rook(player));
            this.board.getField(player.getRelativePosition(new Position(0, 1))).setPiece(new Knight(player));
            this.board.getField(player.getRelativePosition(new Position(0, 2))).setPiece(new Bishop(player));
            this.board.getField(player.getRelativePosition(new Position(0, 3))).setPiece(new Queen(player));
            this.board.getField(player.getRelativePosition(new Position(0, 4))).setPiece(new King(player));
            this.board.getField(player.getRelativePosition(new Position(0, 5))).setPiece(new Bishop(player));
            this.board.getField(player.getRelativePosition(new Position(0, 6))).setPiece(new Knight(player));
            this.board.getField(player.getRelativePosition(new Position(0, 7))).setPiece(new Rook(player));
            for (int i = 0; i < 8; i++) {
                this.board.getField(player.getRelativePosition(new Position(1, i))).setPiece(new Pawn(player));
            }
        }
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public boolean canTakeBack() {
        return !this.moves.isEmpty();
    }

    public void makeMove(Move move) {
        ChessPiece piece = this.board.getField(move.getStart()).getPiece();
        if (piece.getOwner() == this.getCurrentPlayer()) {
            piece.makeMove(move);
        }

        this.moves.addLast(move);
        this.currentPlayer = this.currentPlayer.opposite();
    }

    public void takeBackMove() {
        Move move = this.moves.getLast();

        ChessPiece piece = this.board.getField(move.getEnd()).getPiece();
        piece.makeMove(move.getReversedMove());

        this.board.getField(move.getEnd()).setPiece(move.getEndPiece());

        this.moves.removeLast();
        this.currentPlayer = this.currentPlayer.opposite();
    }

    private final ChessBoard board;
    private final LinkedList<Move> moves = new LinkedList<Move>();
    private Player currentPlayer = Player.WHITE;
}
