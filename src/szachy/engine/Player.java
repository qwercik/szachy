package szachy.engine;

import szachy.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Player {
    WHITE, BLACK;

    Player() {
        this.king = new King(this);
        this.queens.add(new Queen(this));

        for (int i = 0; i < 2; i++) {
            this.rooks.add(new Rook(this));
            this.bishops.add(new Bishop(this));
            this.knights.add(new Knight(this));
        }

        for (int i = 0; i < 8; i++) {
            this.pawns.add(new Pawn(this));
        }
    }

    public King getKing() {
        return this.king;
    }

    public ArrayList<Queen> getQueens() {
        return this.queens;
    }

    public ArrayList<Rook> getRooks() {
        return this.rooks;
    }

    public ArrayList<Bishop> getBishops() {
        return this.bishops;
    }

    public ArrayList<Knight> getKnights() {
        return this.knights;
    }

    public ArrayList<Pawn> getPawns() {
        return this.pawns;
    }



    public Player opposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    public Position getRelativePosition(Position position) {
        if (this == BLACK) {
            return position;
        }

        return new Position(ChessBoard.SIZE - 1 - position.getRow(), position.getColumn());
    }

    public String getPluralName() {
        return this == WHITE ? "białe" : "czarne";
    }

    private King king;
    private ArrayList<Queen> queens = new ArrayList<>();
    private ArrayList<Rook> rooks = new ArrayList<>();
    private ArrayList<Knight> knights = new ArrayList<>();
    private ArrayList<Bishop> bishops = new ArrayList<>();
    private ArrayList<Pawn> pawns = new ArrayList<>();
}