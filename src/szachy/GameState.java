package szachy;

import szachy.pieces.*;

import java.util.LinkedList;

public class GameState {
    private ChessBoard board;
    private Player player = Player.WHITE;
    private LinkedList<Move> moves = new LinkedList<Move>();
    private Field activeField;
    private ControlPanel controlPanel;

    public GameState() {
        this.initChessboard();
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public ChessBoard getChessBoard() {
        return this.board;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean canTakeBack() {
        return !this.moves.isEmpty();
    }

    public void makeMove(Move move) {
        this.makeMoveWithoutRegisteringIt(move);
        this.moves.addLast(move);
        this.player = this.player.toggle();
        this.controlPanel.update();
    }

    public void takeBackMove() {
        Move move = this.moves.getLast();
        this.makeMoveWithoutRegisteringIt(move.opposite());
        this.moves.removeLast();
        this.player = this.player.toggle();
        this.controlPanel.update();
    }

    private void makeMoveWithoutRegisteringIt(Move move) {
        Field startField = this.board.getField(move.getStart());
        Field endField = this.board.getField(move.getEnd());

        endField.setPiece(startField.getPiece());
        startField.setPiece(null);
    }

    public void setActiveField(Field field) {
        this.activeField = field;
    }
    
    public Field getActiveField() {
        return this.activeField;
    }

    private void initChessboard() {
        this.board = new ChessBoard(this);

        this.board.setPiece(new Position(0, 0), new Rook(Player.BLACK));
        this.board.setPiece(new Position(0, 1), new Knight(Player.BLACK));
        this.board.setPiece(new Position(0, 2), new Bishop(Player.BLACK));
        this.board.setPiece(new Position(0, 3), new Queen(Player.BLACK));
        this.board.setPiece(new Position(0, 4), new King(Player.BLACK));
        this.board.setPiece(new Position(0, 5), new Bishop(Player.BLACK));
        this.board.setPiece(new Position(0, 6), new Knight(Player.BLACK));
        this.board.setPiece(new Position(0, 7), new Rook(Player.BLACK));
        for (int i = 0; i < 8; i++) {
            this.board.setPiece(new Position(1, i), new Pawn(Player.BLACK));
        }

        this.board.setPiece(new Position(7, 0), new Rook(Player.WHITE));
        this.board.setPiece(new Position(7, 1), new Knight(Player.WHITE));
        this.board.setPiece(new Position(7, 2), new Bishop(Player.WHITE));
        this.board.setPiece(new Position(7, 3), new Queen(Player.WHITE));
        this.board.setPiece(new Position(7, 4), new King(Player.WHITE));
        this.board.setPiece(new Position(7, 5), new Bishop(Player.WHITE));
        this.board.setPiece(new Position(7, 6), new Knight(Player.WHITE));
        this.board.setPiece(new Position(7, 7), new Rook(Player.WHITE));
        for (int i = 0; i < 8; i++) {
            this.board.setPiece(new Position(6, i), new Pawn(Player.WHITE));
        }
    }
}
