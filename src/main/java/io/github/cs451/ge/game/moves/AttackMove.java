package io.github.cs451.ge.game.moves;

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
    public boolean mustBeTaken() {
        return true;
    }
}
