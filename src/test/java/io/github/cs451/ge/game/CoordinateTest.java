package io.github.cs451.ge.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {
    private Coordinate coordinate;

    @Before
    public void makeCoordinate() {
        coordinate = new Coordinate(5, 5);
    }

    @Test
    public void testCoordinateDirectionTopLeft() {
        Coordinate newCoordinate = coordinate.apply(Coordinate.Direction.TOP_LEFT);

        assertEquals(newCoordinate.getRow(), coordinate.getRow() - 1);
        assertEquals(newCoordinate.getColumn(), coordinate.getColumn() - 1);
    }

    @Test
    public void testCoordinateTopRight() {
        Coordinate newCoordinate = coordinate.apply(Coordinate.Direction.TOP_RIGHT);

        assertEquals(newCoordinate.getRow(), coordinate.getRow() - 1);
        assertEquals(newCoordinate.getColumn(), coordinate.getColumn() + 1);
    }

    @Test
    public void testCoordinateDirectionBottomLeft() {
        Coordinate newCoordinate = coordinate.apply(Coordinate.Direction.BOTTOM_LEFT);

        assertEquals(newCoordinate.getRow(), coordinate.getRow() + 1);
        assertEquals(newCoordinate.getColumn(), coordinate.getColumn() - 1);
    }

    @Test
    public void testCoordinateDirectionBottomRight() {
        Coordinate newCoordinate = coordinate.apply(Coordinate.Direction.BOTTOM_RIGHT);

        assertEquals(newCoordinate.getRow(), coordinate.getRow() + 1);
        assertEquals(newCoordinate.getColumn(), coordinate.getColumn() + 1);
    }
}
