package szachy;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import szachy.pieces.*;

import java.util.LinkedList;

public class GameState {
    private ChessBoard board;
    private Player player = Player.WHITE;
    private LinkedList<Move> moves = new LinkedList<Move>();
    private Field activeField;
    private ControlPanel controlPanel;
    private Stage stage;

    public GameState(Stage stage) {
        this.stage = stage;
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
        ChessPiece piece = this.getChessBoard().getField(move.getStart()).getPiece();
        piece.makeMove(move);
        this.moves.addLast(move);

        this.player = this.player.toggle();
        this.controlPanel.update();
    }

    public void takeBackMove() {
        Move move = this.moves.getLast();
        this.moves.removeLast();

        ChessPiece piece = this.board.getField(move.getEnd()).getPiece();
        piece.makeMove(move.opposite());

        this.board.getField(move.getEnd()).setPiece(move.getRemovedPiece());

        this.player = this.player.toggle();
        this.controlPanel.update();
    }

    public void setActiveField(Field field) {
        this.activeField = field;
    }
    
    public Field getActiveField() {
        return this.activeField;
    }

    public void endGameWithDraw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText("Koniec gry");
        alert.setContentText("Gra zakończyła się remisem");

        alert.showAndWait();
        this.stage.close();
    }

    public void endGameWithWin(Player winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Koniec gry");
        alert.setHeaderText("Koniec gry");
        String winnerText = (winner == Player.WHITE) ? "białych" : "czarnych";
        alert.setContentText("Gra zakończyła się zwycięstwem " + winnerText);

        alert.showAndWait();
        this.stage.close();
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
