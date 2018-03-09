package io.github.cs451.ge.game.moves;

import io.github.cs451.ge.game.Checkers;
import io.github.cs451.ge.game.pieces.Piece;
import lombok.Data;

@Data
public abstract class Move implements Comparable<Move> {
    private final Piece from;
    private final Piece to;

    protected Move(Piece from, Piece to) {
        this.from = from;
        this.to = to;
    }

    public boolean mustBeTaken() {
        return false;
    }

    public abstract int getWeight();

    @Override
    public int compareTo(Move o) {
        return getWeight() - o.getWeight();
    }

    public abstract void apply(Checkers checkers);
}
