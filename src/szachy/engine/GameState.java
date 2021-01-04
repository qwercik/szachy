package szachy.engine;

import javafx.util.Pair;
import szachy.pieces.*;

import java.util.LinkedList;

public class GameState {
    public GameState() {
        this.board = new ChessBoard(this);

        for (Player player : new Player[] {Player.BLACK, Player.WHITE}) {
            this.board.getField(player.getRelativePosition(new Position(0, 0))).setPiece(player.getRooks().get(0));
            this.board.getField(player.getRelativePosition(new Position(0, 1))).setPiece(player.getKnights().get(0));
            this.board.getField(player.getRelativePosition(new Position(0, 2))).setPiece(player.getBishops().get(0));
            this.board.getField(player.getRelativePosition(new Position(0, 3))).setPiece(player.getQueens().get(0));
            this.board.getField(player.getRelativePosition(new Position(0, 4))).setPiece(player.getKing());
            this.board.getField(player.getRelativePosition(new Position(0, 5))).setPiece(player.getBishops().get(1));
            this.board.getField(player.getRelativePosition(new Position(0, 6))).setPiece(player.getKnights().get(1));
            this.board.getField(player.getRelativePosition(new Position(0, 7))).setPiece(player.getRooks().get(1));
            for (int i = 0; i < 8; i++) {
                this.board.getField(player.getRelativePosition(new Position(1, i))).setPiece(player.getPawns().get(i));
            }
        }
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public LinkedList<Move> getMovesHistory() {
        return this.movesHistory;
    }

    public boolean canTakeBack() {
        return !this.movesHistory.isEmpty();
    }

    public void makeMove(Move move) {
        ChessPiece piece = this.board.getField(move.getStart()).getPiece();

        piece.makeMove(move);
        this.movesHistory.addLast(move);
        this.currentPlayer = this.currentPlayer.opposite();
    }

    public void takeBackMove() {
        Move move = this.movesHistory.getLast();

        ChessPiece piece = move.getMovedPiece();
        piece.takeBackMove(move);

        this.movesHistory.removeLast();
        this.currentPlayer = this.currentPlayer.opposite();
    }

    public boolean verifyMoveForCheck(Move move) {
        return true;
        /*
        ChessPiece piece = this.board.getField(move.getStart()).getPiece();

        piece.makeMove(move);
        boolean result = !this.isCheck();
        piece.makeMove(move.getReversedMove());
        this.board.getField(move.getEnd()).setPiece(move.getEndPiece());

        return result;
        */
    }

    public boolean isCheck() {
        Player attacker = this.getCurrentPlayer().opposite();
        Position kingPosition = this.currentPlayer.getKing().getField().getPosition();
        return this.isAttacked(kingPosition, attacker);
    }

    public boolean isAttacked(Position position, Player attacker) {
        return false;/*
        for (Field[] row : this.board.getAllFields()) {
            for (Field field : row) {
                ChessPiece piece = field.getPiece();
                if (piece != null && piece.getOwner() == attacker) {
                    for (Move move : piece.getAllPossibleMoves()) {
                        if (move.getEnd().equals(position)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;*/
    }


    public void update() {
        if (this.isCheck()) {
            this.markKingAsChecked();
        }

        this.board.update();
    }


    private void markKingAsChecked() {
        Field kingField = this.currentPlayer.getKing().getField();
        kingField.setState(kingField.getState().withCheck(true));
    }


    private final ChessBoard board;
    private final LinkedList<Move> movesHistory = new LinkedList<Move>();
    private Player currentPlayer = Player.WHITE;
}
