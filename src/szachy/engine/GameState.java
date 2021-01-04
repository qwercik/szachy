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

    public boolean canTakeBack() {
        return !this.moves.isEmpty();
    }

    public void makeMove(Move move) {
        ChessPiece piece = this.board.getField(move.getStart()).getPiece();

        piece.makeMove(move);
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

    public boolean verifyMoveForCheck(Move move) {
        ChessPiece piece = this.board.getField(move.getStart()).getPiece();

        piece.makeMove(move);
        boolean result = !this.isPlayerChecked(this.getCurrentPlayer());
        piece.makeMove(move.getReversedMove());
        this.board.getField(move.getEnd()).setPiece(move.getEndPiece());

        return result;
    }

    public boolean isCheck() {
        for (Field[] row : this.board.getAllFields()) {
            for (Field field : row) {
                if (field.getState().isCheck()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isPlayerChecked(Player checked) {
        for (Field[] row : this.board.getAllFields()) {
            for (Field field : row) {
                if (field.getState().isCheck() && field.getPiece().getOwner() == checked) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isAttacked(Position position, Player attacker) {
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

        return false;
    }

    public void handleCheckIfOccurred() {
        Player potentiallyChecked = this.getCurrentPlayer();
        for (Field[] row : this.getBoard().getAllFields()) {
            for (Field field : row) {
                ChessPiece piece = field.getPiece();
                if (piece != null && piece.getType() == ChessPiece.Type.KING && piece.getOwner() == potentiallyChecked) {
                    Player attacker = potentiallyChecked.opposite();
                    if (this.isAttacked(field.getPosition(), attacker)) {
                        field.setState(field.getState().withCheck(true));
                    }

                    return;
                }
            }
        }
    }

    public void update() {
        this.board.update();
    }

    private final ChessBoard board;
    private final LinkedList<Move> moves = new LinkedList<Move>();
    private Player currentPlayer = Player.WHITE;
}
