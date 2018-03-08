package io.github.cs451.ge.game;

import io.github.cs451.ge.game.pieces.EmptyPiece;
import io.github.cs451.ge.game.pieces.NormalPiece;
import io.github.cs451.ge.game.pieces.Piece;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CheckersRow {
    private final List<Piece> pieces;
    private final int size;
    private final int row;

    public CheckersRow(int size, int row) {
        this.size = size;
        this.row = row;
        pieces = new ArrayList<>(size);
        clear();
    }

    public void addPiece(Piece piece, Coordinate coordinate) {
        pieces.set(coordinate.getX(), piece);
    }

    private void clear() {
        pieces.clear();
        for (int i = 0; i < size; i++) {
            pieces.add(new EmptyPiece(new Coordinate(i, row)));
        }
    }

    public void reset(boolean offset, CheckersPlayer player) {
        clear();
        int starter = 0;
        if (offset)
            starter = 1;

        for (int i = starter; i < size; i += 2) {
            pieces.set(i, new NormalPiece(player, new Coordinate(i, row)));
        }
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
