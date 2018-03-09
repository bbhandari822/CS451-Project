package io.github.cs451.ge.game.moves;

import io.github.cs451.ge.game.pieces.Piece;

public class KingMove extends Move {
    public KingMove(Piece from, Piece to) {
        super(from, to);
    }

    @Override
    public int getWeight() {
        return 10;
    }

}
