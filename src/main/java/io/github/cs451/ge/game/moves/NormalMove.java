package io.github.cs451.ge.game.moves;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.pieces.EmptyPiece;
import io.github.cs451.ge.game.pieces.KingPiece;
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
        Piece piece = getFrom().moveTo(getTo().getCoordinate());
        if (checkers.isAtBorder(getTo().getCoordinate())) {
            piece = new KingPiece(getFrom().getPlayer(), getTo().getCoordinate());
        }
        checkers.setPiece(new EmptyPiece(getFrom().getCoordinate()));
        checkers.setPiece(piece);
    }
}
