package io.github.cs451.ge.game.moves;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.pieces.EmptyPiece;
import io.github.cs451.ge.game.pieces.Piece;

public class AttackMove extends Move {
    private final Piece kill;

    public AttackMove(Piece from, Piece kill, Piece to) {
        super(from, to);
        this.kill = kill;
    }

    @Override
    public int getWeight() {
        return 99;
    }

    @Override
    public void apply(Checkers checkers) {
        Piece piece = getFrom().moveTo(getTo().getCoordinate());
        checkers.setPiece(new EmptyPiece(getFrom().getCoordinate()));
        checkers.setPiece(new EmptyPiece(kill.getCoordinate()));

        checkers.setPiece(piece);
    }

    @Override
    public boolean mustBeTaken() {
        return true;
    }
}
