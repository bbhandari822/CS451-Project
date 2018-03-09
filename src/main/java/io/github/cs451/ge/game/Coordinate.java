package io.github.cs451.ge.game;

import lombok.Data;

import java.util.function.Function;

@Data
public class Coordinate {
    // Row - This is used to find the CheckersRow
    private final int row;
    // Column - This is whats used in CheckersRow
    private final int column;

    /**
     * Returns a new coordinate, doesn't touch the current coordinate.
     *
     * @param direction
     * @return
     */
    public Coordinate apply(Direction direction) {
        return direction.apply(this);
    }

    public enum Direction {
        TOP_LEFT(coordinate -> {
            return new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() - 1);
        }),
        TOP_RIGHT(coordinate -> {
            return new Coordinate(coordinate.getRow() - 1, coordinate.getColumn() + 1);
        }),
        BOTTOM_LEFT(coordinate -> {
            return new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() - 1);
        }),
        BOTTOM_RIGHT(coordinate -> {
            return new Coordinate(coordinate.getRow() + 1, coordinate.getColumn() + 1);
        });
        private final Function<Coordinate, Coordinate> function;

        Direction(Function<Coordinate, Coordinate> function) {
            this.function = function;
        }

        private Coordinate apply(Coordinate coordinate) {
            return this.function.apply(coordinate);
        }
    }
}
