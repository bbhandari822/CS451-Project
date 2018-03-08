package io.github.cs451.ge.game;

import io.github.cs451.ge.game.pieces.EmptyPiece;
import io.github.cs451.ge.game.pieces.Piece;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CheckersRow {
    private final List<Piece> pieces;

    public CheckersRow(int size) {
        pieces = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            pieces.add(new EmptyPiece());
        }
    }

    public void addPiece(Piece piece, Coordinate coordinate) {
        pieces.set(coordinate.getX(), piece);
    }
}
