package io.github.cs451.ge.game.moves;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.pieces.EmptyPiece;
import io.github.cs451.ge.game.pieces.Piece;

public class NormalMove extends Move {
    public NormalMove(Piece from, Piece to) {
        super(from, to);
    }

    @Override
    public int getWeight() {
        return 10;
    }

    @Override
    public void apply(Checkers checkers) {
        Piece piece = pieceUpgradeHandler(checkers);

        checkers.setPiece(new EmptyPiece(getFrom().getCoordinate()));
        checkers.setPiece(piece);
    }
}
