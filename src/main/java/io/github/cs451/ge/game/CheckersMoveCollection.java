package io.github.cs451.ge.game;

import io.github.cs451.ge.game.moves.Move;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;


@ToString
public class CheckersMoveCollection implements Iterable<Move> {
    private final List<Move> moves = new ArrayList<>();

    public CheckersMoveCollection() {
    }

    public CheckersMoveCollection(Collection<? extends Move> collection) {
        addAll(collection);
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public boolean hasAnAttackMove() {
        return moves.stream().anyMatch(Move::mustBeTaken);
    }

    @Override
    public Iterator<Move> iterator() {
        return moves.iterator();
    }

    public void addAll(Collection<? extends Move> collection) {
        moves.addAll(collection);
    }

    public void addAll(CheckersMoveCollection collection) {
        moves.addAll(collection.moves);
    }

    public Move get(int index) {
        return moves.get(index);
    }

    public boolean isEmpty() {
        return moves.isEmpty();
    }

    public void applyFormatting() {
        Stream<Move> stream = moves.stream();
        if (hasAnAttackMove()) {
            stream = stream.filter(Move::mustBeTaken);
        }
        stream.forEach(move -> move.getTo().setUsed(true));

    }
}
